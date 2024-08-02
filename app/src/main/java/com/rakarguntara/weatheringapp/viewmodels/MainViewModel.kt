package com.rakarguntara.weatheringapp.viewmodels

import androidx.lifecycle.ViewModel
import com.rakarguntara.weatheringapp.models.WeatherModelResponse
import com.rakarguntara.weatheringapp.network.ResponseState
import com.rakarguntara.weatheringapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    suspend fun getForecastDaily(city: String, units: String = "Imperial"): ResponseState<WeatherModelResponse, Boolean, Exception>{
        return repository.getForecastDaily(city, units)
    }

}