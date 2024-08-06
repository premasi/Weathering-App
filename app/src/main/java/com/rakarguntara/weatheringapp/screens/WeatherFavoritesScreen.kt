package com.rakarguntara.weatheringapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rakarguntara.weatheringapp.R
import com.rakarguntara.weatheringapp.viewmodels.FavoriteViewModel
import com.rakarguntara.weatheringapp.widgets.WeatherAppBar
import com.rakarguntara.weatheringapp.widgets.WeatherFavoriteListItem

@Composable
fun WeatherFavoritesScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        WeatherAppBar(title = "Favorites",
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            navController =  navController,
            isMainScreen = false,
            onButtonClicked = {
                navController.popBackStack()
            })
    }) {
        Surface(modifier = Modifier.padding(it).fillMaxSize(),
            color = colorResource(R.color.gray)
        ){
            val listFavorite = favoriteViewModel.favList.collectAsState().value
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(items = listFavorite){ data ->
                    WeatherFavoriteListItem(data, navController, favoriteViewModel)
                }
            }
        }
    }
}