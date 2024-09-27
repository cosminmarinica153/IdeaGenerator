package com.cosmin.ideasgenerator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LLMResponse {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        @JsonProperty("message")
        private Message message;

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Message {
        @JsonProperty("content")
        private String content;

        public String getContent() {
            return content
                    .replace("\\\\", "\\") // Escape backslashes
                    .replace("\\\"", "\"") // Escape double quotes
                    .replace("\\b", "\b")   // Escape backspace
                    .replace("\\f", "\f")   // Escape form feed
                    .replace("\\n", "\n")   // Escape new line
                    .replace("\\r", "\r")   // Escape carriage return
                    .replace("\\t", "\t");
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    @JsonProperty("id")
    private String id;
    @JsonProperty("choices")
    private Choice[] choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Choice[] getChoices() {
        return choices;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }
}
