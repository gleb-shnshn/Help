package com.papalam.help.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Form {
    public String description;
    ArrayList<Question> questions;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    String icon;

    @SerializedName("form_name")
    String formName;

    @SerializedName("form_id")
    int formId;

    public Form(String formName, String description, String icon) {
        this.description = description;
        this.icon = icon;
        this.formName = formName;
    }

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
