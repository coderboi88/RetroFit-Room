package com.example.retrofitroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofitroom.Adapter.ActorAdapter;
import com.example.retrofitroom.Model.Actor;
import com.example.retrofitroom.Network.RetroFit;
import com.example.retrofitroom.Repositeries.ActorRepositery;
import com.example.retrofitroom.ViewModel.ActorViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActorViewModel actorViewModel;
    private RecyclerView recyclerView;
    private List<Actor> actorList;
    private ActorRepositery actorRepositery;
    private ActorAdapter actorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actorRepositery = new ActorRepositery(getApplication());
        actorList = new ArrayList<>();
        actorAdapter = new ActorAdapter(this, actorList);
        actorViewModel = new ViewModelProvider(this).get(ActorViewModel.class);

        networkRequest();
        actorViewModel.getAllActor().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> actorList) {
                recyclerView.setAdapter(actorAdapter);
                actorAdapter.getAllActors(actorList);

                Log.d("main", "onChanged: " + actorList);
            }


        });
    }
        private void networkRequest() {

            RetroFit retrofit=new RetroFit();
            Call<List<Actor>> call=retrofit.api.getAllActors();
            call.enqueue(new Callback<List<Actor>>() {
                @Override
                public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                    if(response.isSuccessful())
                    {
                        actorRepositery.insert(response.body());
                        Log.d("main", "onResponse: "+response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Actor>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "something went wrong...", Toast.LENGTH_SHORT).show();
                }
            });

        }

}
