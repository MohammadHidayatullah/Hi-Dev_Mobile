package com.example.mobileiklanku.api.services;

import com.example.mobileiklanku.api.models.LokerModels;
import com.example.mobileiklanku.api.models.WebinarModels;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api/webinar")
    Call<List<WebinarModels>> getWebinar();
    @GET("/api/loker")
    Call<List<LokerModels>> getLoker();
}
