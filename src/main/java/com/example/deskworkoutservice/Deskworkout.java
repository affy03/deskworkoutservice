package com.example.deskworkoutservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deskworkouts")
public class Deskworkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 200, nullable = false, columnDefinition = "VARCHAR(200) DEFAULT '未設定'")
    private String howto;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer repetition;
    @Column(length = 100, nullable = false, columnDefinition = "VARCHAR(100) DEFAULT '未設定'")
    private String bodyparts;

    @Column(length = 20, nullable = false, columnDefinition = "VARCHAR(20) DEFAULT '未設定'")
    private String difficulty;

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

    public Deskworkout() {
        // デフォルトコンストラクタ
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHowto(String howto) {
        this.howto = howto;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public void setBodyparts(String bodyparts) {
        this.bodyparts = bodyparts;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
