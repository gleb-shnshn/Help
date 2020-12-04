package com.papalam.help.model;

import com.google.gson.annotations.SerializedName;

public class LoginAndPassword {
    @SerializedName("login")
    String login;
    @SerializedName("password")
    String password;
    @SerializedName("name")
    String name;

    public LoginAndPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
