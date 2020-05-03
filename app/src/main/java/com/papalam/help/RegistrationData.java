package com.papalam.help;

import com.google.gson.annotations.SerializedName;

public class RegistrationData {
    @SerializedName("login")
    String login;

    @SerializedName("password")
    String password;

    @SerializedName("doctorId")
    String doctorId;

    @SerializedName("name")
    String name;


    public RegistrationData(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }
}
