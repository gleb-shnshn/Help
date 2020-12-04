package com.papalam.help.model;

import com.google.gson.annotations.SerializedName;

public class RegistrationData {
    @SerializedName("login")
    String login;

    @SerializedName("password")
    String password;

    @SerializedName("name")
    String name;


    public RegistrationData(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }
}
