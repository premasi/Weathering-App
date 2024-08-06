package com.rakarguntara.weatheringapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
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
import com.rakarguntara.weatheringapp.R
import com.rakarguntara.weatheringapp.models.FavoriteModelLocalResponse
import com.rakarguntara.weatheringapp.navigation.WeatherScreens
import com.rakarguntara.weatheringapp.viewmodels.FavoriteViewModel

@Composable
fun WeatherFavoriteListItem(
    favoriteModelLocalResponse: FavoriteModelLocalResponse,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
){
    Surface(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        .clickable {
            if(favoriteModelLocalResponse.city == "Jakarta"){
                navController.navigate(WeatherScreens.MainScreen.name+"/${favoriteModelLocalResponse.city}"){
                    popUpTo(navController.graph.startDestinationId){
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(WeatherScreens.MainScreen.name+"/${favoriteModelLocalResponse.city}")
            }

        },
        shape = RoundedCornerShape(8.dp),
        color = colorResource(R.color.blue)
    ){
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            Text(favoriteModelLocalResponse.city, style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.gray)
            ))

            Surface(
                shape = CircleShape,
                color = colorResource(R.color.beige)
            ) {
                Text(favoriteModelLocalResponse.country, style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = colorResource(R.color.black),
                ), modifier = Modifier.padding(8.dp))
            }

            Icon(Icons.Default.Delete, contentDescription = "delete", tint = Color.Red,
                modifier = Modifier.clickable {
                    favoriteViewModel.deleteFavoriteById(favoriteModelLocalResponse.city)
                })
        }
    }

}