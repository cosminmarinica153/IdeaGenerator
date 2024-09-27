package com.cosmin.ideasgenerator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Prompt {
    private class Messages {
        private String role;
        private String content;

        public Messages(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    @JsonIgnore
    private final String promptText =
            """
                     We want 5 ideas of applications using 2-5 API services from the list below. The applications must be simple to implement, small, and instructive for junior Java programmers.
                    
                     Act as a professional senior Java programmer and tutor. You are also versatile in business evaluations and quite creative.
                    
                     Please start each idea with this separator: *****
                    
                     Ensure that each idea strictly follows the format provided below. Do not include anything outside of this format. Each section must be formatted as plain text, and there should be no new lines between sections. The response should be continuous and aligned with the categories as shown.
                    
                     Each category must be followed by: : and a space, never a new line.
                    
                     Format:
                    
                     *****
                     Idea Number: {idea_number}
                    
                     Attractive Business Title: {title}
                    
                     Slogan: {slogan}
                    
                     Short description (5-7 words): {short_description}
                    
                     Detailed description (5-7 paragraphs): {detailed_description}
                    
                     Elevator pitch: {elevator_pitch}
                    
                     Difficulty to implement (on a scale of 1-5): {difficulty}
                    
                     Chance of success to public (on a scale of 1-5): {chance_of_success}
                    
                     Tags: {tags}
                    
                     Here is the list of APIs you can use:
                    
                     Here are the list of apis I want to use:
                   
                    \s""";
    @JsonIgnore
    private final ApiData[] apis;

    private Messages[] messages;
    private final String model = "mixtral-8x7b-32768";

    public Prompt(ApiData[] apis) {
        this.apis = apis;

        String completedPrompt = generatePromt();

        Messages messages = new Messages("user", completedPrompt);
        this.messages = new Messages[1];
        this.messages[0] = messages;
    }

    public String getPromptText() {
        return promptText;
    }

    public ApiData[] getApis() {
        return apis;
    }

    public Messages[] getMessages() {
        return messages;
    }

    public void setMessages(Messages[] messages) {
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    private String generatePromt() {
        StringBuilder prompt = new StringBuilder();

        prompt.append(promptText);

        for (ApiData api : apis) {
            prompt.append("\n");
            prompt.append(api.toString());
        }

        String completePrompt = prompt.toString();

        completePrompt = completePrompt
                .replace("\\", "\\\\") // Escape backslashes
                .replace("\"", "\\\"") // Escape double quotes
                .replace("\b", "\\b")   // Escape backspace
                .replace("\f", "\\f")   // Escape form feed
                .replace("\n", "\\n")   // Escape new line
                .replace("\r", "\\r")   // Escape carriage return
                .replace("\t", "\\t");  // Escape tab

        return completePrompt;
    }
}
