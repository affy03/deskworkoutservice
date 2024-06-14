package com.example.deskworkoutservice;

public class Deskworkout {
    private final int id;

    private final String name;

    private final String howto;

    private final int repetition;

    private final String bodyparts;

    private final String difficulty;

    public Deskworkout(int id, String name, String howto, int repetition, String bodyparts, String difficulty) {
        this.id = id;
        this.name = name;
        this.howto = howto;
        this.repetition = repetition;
        this.bodyparts = bodyparts;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
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
