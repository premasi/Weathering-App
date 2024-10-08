package com.rakarguntara.weatheringapp.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.rakarguntara.weatheringapp.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavHostController) {
    val scaleAnimationState = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scaleAnimationState.animateTo(targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                }
            )
        )

        delay(1000)
        navController.navigate("${WeatherScreens.MainScreen.name}/Jakarta"){
            popUpTo(navController.graph.startDestinationId){
                inclusive = true
            }
        }

    })

    Surface(modifier = Modifier
        .padding(16.dp)
        .size(300.dp)
        .scale(scaleAnimationState.value),
        color = Color.Transparent,
        shape = CircleShape,
        border = BorderStroke(width = 2.dp,
            color = colorResource(R.color.navy)
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
                color = colorResource(R.color.navy),
                fontWeight = FontWeight.Bold,

            ),
                modifier = Modifier.padding(top = 8.dp))

        }

    }
}