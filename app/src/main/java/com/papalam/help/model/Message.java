package com.papalam.help.model;

import android.graphics.drawable.Drawable;

public class Message {
    String name;
    String text;
    Drawable bd;
    int color;
    boolean isMine;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Drawable getBd() {
        return bd;
    }

    public void setBd(Drawable bd) {
        this.bd = bd;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Message(String name, String text, int id, boolean isMine) {
        this.name = name;
        this.text = text;
        this.id = id;
        this.isMine = isMine;
    }
}
