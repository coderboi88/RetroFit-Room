package com.example.retrofitroom.Repositeries;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.retrofitroom.Dao.ActorDao;
import com.example.retrofitroom.Database.ActorDatabase;
import com.example.retrofitroom.Model.Actor;

import java.util.List;

public class ActorRepositery {
    private ActorDatabase database;
    LiveData<List<Actor>> getAllActors;

    public ActorRepositery(Application application){
        database = ActorDatabase.getInstance(application);
        getAllActors = database.actorDao().getAllActor();
    }

    public void insert(List<Actor> actorList){
        new InsertAsyncTask(database).execute(actorList);
    }

    public LiveData<List<Actor>> getAllActor(){
        return getAllActors;
    }

    static class InsertAsyncTask extends AsyncTask<List<Actor>,Void,Void>{
        private ActorDao actorDao;

        InsertAsyncTask(ActorDatabase actorDatabase){
            actorDao = actorDatabase.actorDao();
        }

        @Override
        protected Void doInBackground(List<Actor>... lists) {
            actorDao.insert(lists[0]);
            return null;
        }
    }
}

