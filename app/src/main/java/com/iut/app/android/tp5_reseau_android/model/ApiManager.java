package com.iut.app.android.tp5_reseau_android.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iut.app.android.tp5_reseau_android.service.FuelService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    final static String BASE_URL = "https://public.opendatasoft.com/api/records/1.0/search/";

    private FuelService fuelService = null;

    private static ApiManager instance;

    private FuelStationCallBack fuelStationCallBack = null;

    public FuelService getFuelService() {
        return fuelService;
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }

        return instance;
    }

    private FuelStationCallBack getFuelStationCallBack(){ return fuelStationCallBack; }

    private ApiManager() {
        createRetrofitFuel();
    }

    private void createRetrofitFuel() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofitFuel = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fuelService = retrofitFuel.create(FuelService.class);
    }

}
