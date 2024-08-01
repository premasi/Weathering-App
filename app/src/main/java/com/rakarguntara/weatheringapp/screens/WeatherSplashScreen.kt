package com.rakarguntara.weatheringapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rakarguntara.weatheringapp.R

@Composable
fun WeatherSplashScreen(navController: NavHostController) {
    Surface(modifier = Modifier
        .padding(16.dp)
        .size(300.dp),
        color = Color.Transparent,
        shape = CircleShape,
        border = BorderStroke(width = 2.dp,
            color = colorResource(R.color.sage2)
            )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painterResource(R.drawable.ic_weather),
                contentDescription = "Icon App",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(130.dp))
            Text("Weathering With App", style = TextStyle(
                fontSize = 16.sp,
                color = colorResource(R.color.sage),
                fontWeight = FontWeight.Bold,

            ),
                modifier = Modifier.padding(top = 8.dp))

        }

    }
}