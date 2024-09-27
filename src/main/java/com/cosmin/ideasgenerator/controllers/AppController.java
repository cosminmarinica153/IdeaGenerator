package com.cosmin.ideasgenerator.controllers;

import com.cosmin.ideasgenerator.services.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
    @Autowired
    private LLMService llmService;

    @GetMapping("/")
    public String index(Model model) {
//        model.addAttribute("response", llmService.askLLM());
        return "index";
    }
}
