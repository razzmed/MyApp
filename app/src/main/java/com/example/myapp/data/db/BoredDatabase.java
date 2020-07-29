package com.example.myapp.data.db;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

import com.example.myapp.data.local.BoredDao;
import com.example.myapp.model.BoredAction;

@Database(entities = {BoredAction.class}, version = BoredDatabase.VERSION, exportSchema = false)
public abstract class BoredDatabase extends RoomDatabase {
    public final static int VERSION = 1;

    public abstract BoredDao boredDao();

}
