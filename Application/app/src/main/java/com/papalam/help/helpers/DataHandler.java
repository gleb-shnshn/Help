package com.papalam.help.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.papalam.help.App;


public class DataHandler {
    private final SharedPreferences SHARED_PREFERENCES;

    public DataHandler() {
        this.SHARED_PREFERENCES = App.getInstance().getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    public String getLogin() {
        return getData("login");
    }

    public void saveLogin(String login) {
        setData("login", login);
    }

    public void setData(String key, String data) {
        SharedPreferences.Editor ed = SHARED_PREFERENCES.edit();
        ed.putString(key, data);
        ed.apply();
    }

    public String getData(String key) {
        return SHARED_PREFERENCES.getString(key, "");
    }
}
