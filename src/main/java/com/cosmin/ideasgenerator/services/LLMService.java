package com.cosmin.ideasgenerator.services;

import com.cosmin.ideasgenerator.models.ApiData;
import com.cosmin.ideasgenerator.models.LLMResponse;
import com.cosmin.ideasgenerator.models.Prompt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class LLMService {
    private Prompt prompt;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final int MAX_RETRIES = 3;

    public LLMService() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
    }

    public ResponseEntity<LLMResponse> askLLM() {
        IOService io = new IOService();
        ApiData[] apiList = io.pickApiList();

        prompt = new Prompt(apiList);

        String apiUrl = "https://api.groq.com/openai/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer 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");
        headers.set("Content-Type", "application/json");

        String promptJson;
        try {
            promptJson = objectMapper.writeValueAsString(prompt);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> entity = new HttpEntity<>(promptJson, headers);
        int retryCount = 0;

        while (retryCount < MAX_RETRIES) {
            try {
                ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

                try {
                    LLMResponse parsedResponse = objectMapper.readValue(response.getBody(), LLMResponse.class);

                    if (parsedResponse.getChoices() != null && parsedResponse.getChoices().length > 0) {
                        return ResponseEntity.ok(parsedResponse);
                    } else {
                        return ResponseEntity.noContent().build(); // No content found
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LLMResponse());
                }

            } catch (HttpClientErrorException.TooManyRequests e) {
                retryCount++;
                long waitTime = extractWaitTime(e);
                System.out.println("Rate limit exceeded, retrying in " + waitTime + " seconds...");
                try {
                    TimeUnit.SECONDS.sleep(waitTime);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread was interrupted while waiting to retry", ie);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Too many request");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LLMResponse());
            }
        }

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new LLMResponse());
    }

    private long extractWaitTime(HttpClientErrorException.TooManyRequests e) {
        // Extract the wait time from the exception message, e.g., "Please try again in 4.576s."
        String message = e.getResponseBodyAsString();
        String waitTimeStr = message.replaceAll("[^\\d.]", "");
        return (long) Math.ceil(Double.parseDouble(waitTimeStr));
    }
}
