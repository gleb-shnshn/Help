package com.papalam.help;

import android.graphics.drawable.Drawable;

public class Message {
    String name;
    String msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public Message(String name, String msg, int id, boolean isMine) {
        this.name = name;
        this.msg = msg;
        this.id = id;
        this.isMine = isMine;
    }
}
