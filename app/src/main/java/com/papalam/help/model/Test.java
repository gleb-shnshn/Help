package com.papalam.help.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Test {

    @SerializedName("description")
    public String description;

    @SerializedName("questions")
    ArrayList<Question> questions;

    @SerializedName("name")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("form_name")
    String formName;

    @SerializedName("form_id")
    int formId;

    @SerializedName("id")
    int id;

    @SerializedName("icon")
    String icon;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
