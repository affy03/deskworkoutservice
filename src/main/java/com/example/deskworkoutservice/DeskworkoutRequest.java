package com.example.deskworkoutservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

//デスクワークアウト情報の登録 更新に使用
public class DeskworkoutRequest {

    @NotBlank(message = "空白は許可されていません")
    @Size(max = 20, message = "名前は{max}文字以内で入力してください")
    private String name;

    @NotBlank(message = "空白は許可されていません")
    @Size(max = 100, message = "動作説明は{max}文字以内で入力してください")
    private String howTo;

    @NotNull(message = "空白は許可されていません")
    @Positive(message = "レップ数は自然数で入力してください")
    private Integer repetition;

    @NotBlank(message = "空白は許可されていません")
    @Size(max = 100, message = "部位は{max}文字以内で入力してください")
    private String bodyParts;

    @NotBlank(message = "空白は許可されていません")
    @Size(max = 20, message = "難易度は{max}文字以内で入力してください")
    private String difficulty;

    public DeskworkoutRequest(String name, String howTo, int repetition, String bodyParts, String difficulty) {
        this.name = name;
        this.howTo = howTo;
        this.repetition = repetition;
        this.bodyParts = bodyParts;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getHowTo() {
        return howTo;
    }

    public int getRepetition() {
        return repetition;
    }

    public String getBodyParts() {
        return bodyParts;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
