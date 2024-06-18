package com.example.deskworkoutservice;

public class DeskworkoutRequest {

    private String name;

    private String howto;

    private int repetition;

    private String bodyparts;

    private String difficulty;

    public DeskworkoutRequest(String name, String howto, int repetition, String bodyparts, String difficulty) {
        this.name = name;
        this.howto = howto;
        this.repetition = repetition;
        this.bodyparts = bodyparts;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getHowto() {
        return howto;
    }

    public int getRepetition() {
        return repetition;
    }

    public String getBodyparts() {
        return bodyparts;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
