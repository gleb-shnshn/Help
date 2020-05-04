package com.papalam.help;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App instance;
    private ServerApi retrofit;
    private DataHandler sharedPrefsHandler;
    private GeneralUtils utils;
    private AppCompatActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        String server = getResources().getString(R.string.server_url);
        Gson gson = new GsonBuilder().create();
        sharedPrefsHandler = new DataHandler();
        utils = new GeneralUtils();
        File httpCacheDirectory = new File(getCacheDir(), "responses");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024))
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(server)
                .client(okHttpClient)
                .build().create(ServerApi.class);

    }


    public GeneralUtils getUtils() {
        return utils;
    }

    public static App getInstance() {
        return instance;
    }

    public ServerApi getRetrofit() {
        return retrofit;
    }

    public DataHandler getDataHandler() {
        return sharedPrefsHandler;
    }

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
