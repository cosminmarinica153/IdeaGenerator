package com.cosmin.ideasgenerator.services;

import com.cosmin.ideasgenerator.models.Idea;
import com.cosmin.ideasgenerator.models.LLMResponse;

public class ParserService {
    public Idea[] parseIdeas(LLMResponse input) {
        String inputContent = input.getChoices()[input.getChoices().length - 1].getMessage().getContent();
        System.out.println(inputContent);

        String[] ideas = inputContent.split("\\*\\*\\*\\*\\*");

        Idea[] ideasArray = new Idea[ideas.length];

        for (int i = 0; i < ideas.length; i++) {
            if (ideas[i].trim().isEmpty()) {
                continue; // Skip any empty sections
            }

            Idea idea = new Idea();

            // Extract and set the properties for each idea
            idea.setNumber(extractInt(ideas[i], "Idea Number:"));
            idea.setTitle(extractString(ideas[i], "Attractive Business Title:"));
            idea.setSlogan(extractString(ideas[i], "Slogan:"));
            idea.setShortDescription(extractString(ideas[i], "Short description:"));
            idea.setDetailedDescription(extractString(ideas[i], "Detailed description:"));
            idea.setElevatorPitch(extractString(ideas[i], "Elevator pitch:"));
            idea.setDifficulty(extractInt(ideas[i], "Difficulty to implement (on a scale of 1-5):"));
            idea.setSuccessChance(extractInt(ideas[i], "Chance of success to public (on a scale of 1-5):"));
            idea.setTags(extractTags(ideas[i], "Tags:"));

            // Add the parsed idea to the list
            ideasArray[i] = idea;
        }

        for (Idea idea : ideasArray) {
            if(idea == null)
                continue;
            System.out.println(idea.toString());
        }

        return ideasArray;
    }

    private static int extractInt(String text, String label) {
        String result = extractString(text, label);
        return result != null ? Integer.parseInt(result) : 0;
    }

    private static String extractString(String text, String label) {
        int start = text.indexOf(label);
        if (start == -1) {
            return null;
        }
        start += label.length();
        int end = text.indexOf('\n', start);
        if (end == -1) {
            end = text.length();
        }
        return text.substring(start, end).trim();
    }

    private static String[] extractTags(String text, String label) {
        String tagsString = extractString(text, label);
        return tagsString != null ? tagsString.split(",\\s*") : new String[0];
    }
}
