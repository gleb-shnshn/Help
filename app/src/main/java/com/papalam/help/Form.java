package com.papalam.help;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Form {
    ArrayList<Question> questions;

    @SerializedName("form_name")
    String formName;

    @SerializedName("form_id")
    int formId;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }
}
