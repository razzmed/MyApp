package com.example.myapp.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapp.model.BoredAction;

import java.util.List;

@Dao
public interface BoredDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(BoredAction boredAction);

    @Query("SELECT * FROM bored_action WHERE 'key'=:key")
    BoredAction get(String key);

    @Query("SELECT * FROM bored_action")
    List <BoredAction> getAll();

    @Delete
    void delete(BoredAction boredAction);
}
