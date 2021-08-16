package com.example.lowescodetest.model.remote

import com.example.lowescodetest.BuildConfig
import com.example.lowescodetest.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET(BuildConfig.END_POINT)
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = BuildConfig.API
    ): Response<ApiResponse>

    companion object {
        fun getApi(): Api {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}