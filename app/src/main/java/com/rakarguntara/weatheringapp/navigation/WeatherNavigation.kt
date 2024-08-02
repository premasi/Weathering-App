package com.rakarguntara.weatheringapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rakarguntara.weatheringapp.screens.WeatherMainScreen
import com.rakarguntara.weatheringapp.screens.WeatherSplashScreen
import com.rakarguntara.weatheringapp.viewmodels.MainViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController)
        }

        composable(WeatherScreens.MainScreen.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
            WeatherMainScreen(navController, mainViewModel)
        }
    }
}