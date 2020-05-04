package com.papalam.help.model;

import com.google.gson.annotations.SerializedName;

public class PainPoint {
    float x;

    float y;
    float r;
    String login;

    @SerializedName("image_path")
    String imagePath;

    String comment;

    public float getR() {
        return r;
    }

    public PainPoint(float x, float y, String comment) {
        this.x = x;
        this.y = y;
        this.comment = comment;
        this.r = 15;
        this.login = "test";
    }

    public void setR(float r) {
        this.r = r;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
