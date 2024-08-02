package com.rakarguntara.weatheringapp.network

import com.rakarguntara.weatheringapp.BuildConfig
import com.rakarguntara.weatheringapp.models.WeatherModelResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiService {
    @GET("data/2.5/forecast/daily")
    suspend fun getForecastDaily(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") id: String = BuildConfig.API_KEY
    ): WeatherModelResponse
}