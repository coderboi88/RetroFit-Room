package com.example.retrofitroom.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofitroom.Model.Actor;

import java.util.List;

@Dao
public interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Actor> actorList);

    @Query("SELECT * FROM actor")
    LiveData<List<Actor>> getAllActor();

    @Query("DELETE FROM actor")
    void deleteAll();
}
