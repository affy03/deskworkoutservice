package com.example.deskworkoutservice;

public class Deskworkout {
    private final Integer id;

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

    public Deskworkout(String name, String howto, int repetition, String bodyparts, String difficulty) {
        // id は INSERT文発行時に MySQLによって自動採番した値が補完されるので null を設定
        this.id = null;
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
