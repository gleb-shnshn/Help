package com.papalam.help;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Question {

    @SerializedName("question_id")
    int questionId;

    @SerializedName("question_text")
    String questionText;
    String type;
    List<String> choices;
    List<Integer> scores;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    public Question(int questionId, String questionText, String type, List<String> choices, List<Integer> scores) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.type = type;
        this.choices = choices;
        this.scores = scores;
    }

}
