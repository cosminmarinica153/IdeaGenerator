package com.cosmin.ideasgenerator.controllers;

import com.cosmin.ideasgenerator.models.LLMResponse;
import com.cosmin.ideasgenerator.services.LLMService;
import com.cosmin.ideasgenerator.services.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
public class IdeaController {
    @Autowired
    private LLMService llmService;
    private ParserService parser;

    public IdeaController() {
        this.llmService = new LLMService();
        this.parser = new ParserService();
    }

    @GetMapping("generateIdea")
    public String generateIdea(RedirectAttributes redirectAttributes) {
        ResponseEntity<LLMResponse> responseEntity = llmService.askLLM();
        redirectAttributes.addFlashAttribute("ideas", parser.parseIdeas(responseEntity.getBody()));

        return "redirect:/";
    }
}
