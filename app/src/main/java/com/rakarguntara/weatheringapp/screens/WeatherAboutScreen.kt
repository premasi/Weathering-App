package com.rakarguntara.weatheringapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rakarguntara.weatheringapp.BuildConfig
import com.rakarguntara.weatheringapp.R
import com.rakarguntara.weatheringapp.widgets.WeatherAppBar

@Composable
fun WeatherAboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(navController = navController,
                title = "About",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                onButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it).fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("API by:", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 16.sp
                ))
                Text(BuildConfig.BASE_URL, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.blue),
                    fontSize = 14.sp
                ))
            }

        }
    }
}