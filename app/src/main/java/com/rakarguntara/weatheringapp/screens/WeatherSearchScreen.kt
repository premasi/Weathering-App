package com.rakarguntara.weatheringapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rakarguntara.weatheringapp.components.SearchBarComponent
import com.rakarguntara.weatheringapp.navigation.WeatherScreens
import com.rakarguntara.weatheringapp.widgets.WeatherAppBar

@Composable
fun WeatherSearchScreen(navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Search",
                navController = navController,
                isMainScreen = false,
                icon = Icons.AutoMirrored.Filled.ArrowBack
            ){
                navController.popBackStack()
            }
        }
    ) {
        Surface(modifier = Modifier.padding(it).fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBarComponent(onSearch = { query ->
                    Log.d("Search Query", "WeatherSearchScreen: $query")
                    if(query == "Jakarta" || query == "jakarta"){
                        navController.popBackStack()
                    } else {
                        navController.navigate("${WeatherScreens.MainScreen.name}/$query")
                    }

                })
            }
        }
    }
}