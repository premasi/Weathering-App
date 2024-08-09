package com.rakarguntara.weatheringapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rakarguntara.weatheringapp.screens.WeatherAboutScreen
import com.rakarguntara.weatheringapp.screens.WeatherFavoritesScreen
import com.rakarguntara.weatheringapp.screens.WeatherMainScreen
import com.rakarguntara.weatheringapp.screens.WeatherSearchScreen
import com.rakarguntara.weatheringapp.screens.WeatherSettingsScreen
import com.rakarguntara.weatheringapp.screens.WeatherSplashScreen
import com.rakarguntara.weatheringapp.viewmodels.MainViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController)
        }

        composable(
            "${WeatherScreens.MainScreen.name}/{city}",
            arguments = listOf(
                navArgument(
                    name = "city",
                    builder = {
                        type = NavType.StringType
                    }
                )
            )
        ){ navArg ->
            navArg.arguments?.getString("city").let { cityValue ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                WeatherMainScreen(navController = navController,mainViewModel = mainViewModel, cityValue =  cityValue!!)
            }
        }

        composable(WeatherScreens.SearchScreen.name){
            WeatherSearchScreen(navController)
        }

        composable(WeatherScreens.AboutScreen.name){
            WeatherAboutScreen(navController)
        }

        composable(WeatherScreens.SettingsScreen.name){
            WeatherSettingsScreen(navController)
        }

        composable(WeatherScreens.FavoriteScreen.name){
            WeatherFavoritesScreen(navController)
        }
    }
}