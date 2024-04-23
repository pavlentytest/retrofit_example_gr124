package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Yandex {
    @GET("/api/v1/predict.json/complete")
    fun complete(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("lang") lang: String,
        @Query("limit") limit: Int
    ) : Call<Response>
}