package com.cosmin.ideasgenerator.models;

import java.util.Arrays;

public class Idea {
    private int number;
    private String title;
    private String slogan;
    private String shortDescription;
    private String detailedDescription;
    private String elevatorPitch;
    private int difficulty;
    private int successChance;
    private String[] tags;

    public Idea() {
        number = 0;
        title = "undefined";
        slogan = "undefined";
        shortDescription = "undefined";
        detailedDescription = "undefined";
        elevatorPitch = "undefined";
        difficulty = 0;
        successChance = 0;
        tags = new String[0];
    }

    public Idea(int number, String title, String slogan, String shortDescription, String detailedDescription,
                String elevatorPitch, int difficulty, int successChance, String[] tags) {
        this.number = number;
        this.title = title;
        this.slogan = slogan;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.elevatorPitch = elevatorPitch;
        this.difficulty = difficulty;
        this.successChance = successChance;
        this.tags = tags;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getElevatorPitch() {
        return elevatorPitch;
    }

    public void setElevatorPitch(String elevatorPitch) {
        this.elevatorPitch = elevatorPitch;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getSuccessChance() {
        return successChance;
    }

    public void setSuccessChance(int successChance) {
        this.successChance = successChance;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Idea{" +
                "number=" + number +
                ", title='" + title + '\'' +
                ", slogan='" + slogan + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", elevatorPitch='" + elevatorPitch + '\'' +
                ", difficulty=" + difficulty +
                ", successChance=" + successChance +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}
