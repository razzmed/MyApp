package com.example.myapp;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapp.data.AppPreferences;
import com.example.myapp.data.BoredApiClient;
import com.example.myapp.data.db.BoredDatabase;
import com.example.myapp.data.local.BoredStorage;

public class App extends Application {

    private static BoredDatabase boredDatabase;

    public static AppPreferences appPreferences;
    public static BoredApiClient boredApiClient;
    public static BoredStorage boredStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        boredDatabase = Room.databaseBuilder(this, BoredDatabase.class, "bored.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();

        boredStorage = new BoredStorage(boredDatabase.boredDao());
        appPreferences = new AppPreferences(this);
        boredApiClient = new BoredApiClient();
    }
}
