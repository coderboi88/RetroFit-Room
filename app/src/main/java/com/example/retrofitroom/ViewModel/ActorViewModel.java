package com.example.retrofitroom.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.retrofitroom.Model.Actor;
import com.example.retrofitroom.Repositeries.ActorRepositery;

import java.util.List;

public class ActorViewModel extends AndroidViewModel {

    private ActorRepositery actorRepositery;
    private LiveData<List<Actor>> getAllActor;

    public ActorViewModel(@NonNull Application application) {
        super(application);
        actorRepositery = new ActorRepositery(application);
        getAllActor = actorRepositery.getAllActor();
    }

    public void insert(List<Actor> list)
    {
        actorRepositery.insert(list);
    }

    public LiveData<List<Actor>> getAllActor()
    {
        return getAllActor;
    }
}
