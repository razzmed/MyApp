package com.example.myapp.data;

import android.util.Log;

import com.example.myapp.model.BoredAction;
import com.google.android.material.snackbar.BaseTransientBottomBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BoredApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    BoredApi client = retrofit.create(BoredApi.class);

    public void getAction(String type, Integer participants, Float price, Float minPrice, Float maxPrice, Float accessibility, Float minAccessibility, Float maxAccessibility, BoredActionCallback callback) {
        Call<BoredAction> call = client.getAction(type, participants, price, minPrice, maxPrice, accessibility, minAccessibility, maxAccessibility, callback);
        Log.d("ololo", call.request().url().toString());

        call.enqueue(new CoreCallback<BoredAction>() {
            @Override
            void onSuccess(BoredAction result) {
                callback.onSuccess(result);
            }

            @Override
            void onFailure(Exception exception) {
                callback.onFailure(exception);
            }
        });
    }

    public interface BoredActionCallback extends BaseCallback<BoredAction> {

    }
    public interface BaseCallback<T> {
        void onSuccess(T result);

        void onFailure(Exception exception);
    }

    private interface BoredApi {

        @GET("api/activity/")
        Call<BoredAction> getAction(@Query("type") String type,
                                    @Query("participants") Integer participants,
                                    @Query("price") Float price,
                                    @Query("minPrice") Float minPrice,
                                    @Query("maxPrice") Float maxPrice,
                                    @Query("accessibility") Float accessibility,
                                    @Query("minAccessibility") Float minAccessibility,
                                    @Query("maxAccessibility") Float maxAccessibility,
                                    @Query("BoredActionCallback") BoredActionCallback callback);
    }
}