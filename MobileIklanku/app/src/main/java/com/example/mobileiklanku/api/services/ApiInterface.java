package com.example.mobileiklanku.api.services;

import com.example.mobileiklanku.api.models.LokerModels;
import com.example.mobileiklanku.api.models.WebinarModels;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("api/webinar")
    Call<List<WebinarModels>> getWebinar();
    @GET("api/webinar/{id_webinar}")
    Call<List<WebinarModels>> getWebinarByID(@Path("id_webinar") int id_webinar);
    @GET("api/loker")
    Call<List<LokerModels>> getLoker();
    @GET("api/loker/{id_loker}")
    Call<List<LokerModels>> getLokerByID(@Path("id_loker") int id_loker);
}
