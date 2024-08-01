package com.rakarguntara.weatheringapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.rakarguntara.weatheringapp.navigation.WeatherNavigation
import com.rakarguntara.weatheringapp.ui.theme.WeatheringAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp(){
    WeatheringAppTheme {
        Surface(contentColor = MaterialTheme.colorScheme.background,
            color = colorResource(R.color.beige),
            modifier = Modifier.fillMaxSize()
            ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherNavigation()

            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    WeatheringAppTheme {
//        Greeting("Android")
//    }
}