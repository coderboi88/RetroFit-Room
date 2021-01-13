package com.example.retrofitroom.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.retrofitroom.Dao.ActorDao;
import com.example.retrofitroom.Model.Actor;

@Database(entities={Actor.class},version = 2)
public abstract class ActorDatabase extends RoomDatabase {
    public abstract ActorDao actorDao();

    private static final String DATABASE_NAME = "ActorDatabase";

    private static volatile ActorDatabase INSTANCE;

    //This Method is used to madeSingleton instance of Database as it is costlier to run multiple instances
    public static ActorDatabase getInstance(Context context){
        if(INSTANCE==null){
            synchronized (ActorDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context,ActorDatabase.class,DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{
        private ActorDao actorDao;

        public PopulateAsyncTask(ActorDatabase actorDatabase){
            actorDao = actorDatabase.actorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            actorDao.deleteAll();
            return null;
        }
    }


}
