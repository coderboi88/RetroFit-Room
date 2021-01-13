package com.example.retrofitroom.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFit {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Url.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public Api api = retrofit.create(Api.class);
}
