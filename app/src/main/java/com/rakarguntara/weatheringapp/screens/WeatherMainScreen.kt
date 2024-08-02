package com.rakarguntara.weatheringapp.screens

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import com.rakarguntara.weatheringapp.models.WeatherModelResponse
import com.rakarguntara.weatheringapp.network.ResponseState
import com.rakarguntara.weatheringapp.viewmodels.MainViewModel

@Composable
fun WeatherMainScreen(navController: NavController, mainViewModel: MainViewModel){
    ShowForecastDaily(mainViewModel)
}

@Composable
fun ShowForecastDaily(mainViewModel: MainViewModel) {
    val forecastDailyData = produceState<ResponseState<WeatherModelResponse, Boolean, Exception>>(initialValue = ResponseState(loading = true)){
        value = mainViewModel.getForecastDaily("Jakarta")
    }.value

    if(forecastDailyData.loading == true){
        CircularProgressIndicator()
    } else if(forecastDailyData.data != null){
        Text(text = "Main Screen ${forecastDailyData.data?.city?.name}")
    }


}