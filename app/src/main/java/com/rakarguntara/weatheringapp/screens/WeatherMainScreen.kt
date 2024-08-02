package com.rakarguntara.weatheringapp.screens

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.rakarguntara.weatheringapp.R
import com.rakarguntara.weatheringapp.models.WeatherModelResponse
import com.rakarguntara.weatheringapp.network.ResponseState
import com.rakarguntara.weatheringapp.viewmodels.MainViewModel
import com.rakarguntara.weatheringapp.widgets.WeatherAppBar

@Composable
fun WeatherMainScreen(navController: NavController, mainViewModel: MainViewModel){
    val forecastDailyData = produceState<ResponseState<WeatherModelResponse, Boolean, Exception>>(
        initialValue = ResponseState(loading = true)
    ){
        value = mainViewModel.getForecastDaily("Jakarta")
    }.value

    if(forecastDailyData.loading == true){
        CircularProgressIndicator()
    } else if (forecastDailyData.data != null){
        WeatherMainScreenScaffold(navController, forecastDailyData.data!!)
    }
}

@Composable
fun WeatherMainScreenScaffold(navController: NavController, data: WeatherModelResponse) {
    Scaffold(
        containerColor = colorResource(R.color.beige),
        topBar = {
            WeatherAppBar(title = data.city?.name!!) {  }
        }){
        it
        WeatherMainScreenScaffoldContent(data)
    }
}

@Composable
fun WeatherMainScreenScaffoldContent(data: WeatherModelResponse) {
    Text("${data.city}")
}
