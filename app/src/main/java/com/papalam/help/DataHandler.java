package com.papalam.help;

import android.content.Context;
import android.content.SharedPreferences;


public class DataHandler {
    private final SharedPreferences SHARED_PREFERENCES;

    public DataHandler() {
        this.SHARED_PREFERENCES = App.getInstance().getSharedPreferences("tokens", Context.MODE_PRIVATE);
    }

    public String getRefreshToken() {
        return SHARED_PREFERENCES.getString("refreshToken", "");
    }

    public String getAccessToken() {
        return SHARED_PREFERENCES.getString("accessToken", "");
    }

    public void saveTokens(String accessToken, String refreshToken) {
        SharedPreferences.Editor ed = SHARED_PREFERENCES.edit();
        ed.putString("accessToken", accessToken);
        ed.putString("refreshToken", refreshToken);
        ed.apply();
    }

}
