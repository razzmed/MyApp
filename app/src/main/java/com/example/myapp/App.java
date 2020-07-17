package com.example.myapp;

import android.app.Application;

import com.example.myapp.data.AppPreferences;
import com.example.myapp.data.BoredApiClient;

public class App extends Application {

    public static AppPreferences appPreferences;
    public static BoredApiClient boredApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        appPreferences = new AppPreferences(this);
        boredApiClient = new BoredApiClient();
    }
}
