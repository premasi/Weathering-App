package com.rakarguntara.weatheringapp.repository

import android.util.Log
import com.rakarguntara.weatheringapp.models.WeatherModelResponse
import com.rakarguntara.weatheringapp.network.ApiService
import com.rakarguntara.weatheringapp.network.ResponseState
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    suspend fun getForecastDaily(city: String, units: String = "Imperial"): ResponseState<WeatherModelResponse, Boolean, Exception>{
        val response = try {
            apiService.getForecastDaily(city, units)
        } catch (e: Exception){
            Log.d("Forecast Daily Repository Ex", "getForecastDaily: $e ")
            return ResponseState(e = e)
        }
        return ResponseState(data = response)
    }
}