package com.rakarguntara.weatheringapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.rakarguntara.weatheringapp.BuildConfig
import com.rakarguntara.weatheringapp.R
import com.rakarguntara.weatheringapp.models.ListItem
import com.rakarguntara.weatheringapp.models.WeatherModelResponse
import com.rakarguntara.weatheringapp.navigation.WeatherScreens
import com.rakarguntara.weatheringapp.network.ResponseState
import com.rakarguntara.weatheringapp.utils.formatDate
import com.rakarguntara.weatheringapp.utils.formatDateTime
import com.rakarguntara.weatheringapp.utils.formatDecimals
import com.rakarguntara.weatheringapp.viewmodels.MainViewModel
import com.rakarguntara.weatheringapp.widgets.WeatherAppBar
import com.rakarguntara.weatheringapp.widgets.WeatherWeekListItem

@Composable
fun WeatherMainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    cityValue: String
){
    Log.d("City Value", "WeatherMainScreen: $cityValue")
    val forecastDailyData = produceState<ResponseState<WeatherModelResponse, Boolean, Exception>>(
        initialValue = ResponseState(loading = true)
    ){
        value = mainViewModel.getForecastDaily(cityValue)
    }.value

    if(forecastDailyData.loading == true){
        CircularProgressIndicator(
            modifier = Modifier.background(Color.Transparent),
            color = colorResource(R.color.navy)
        )
    } else if (forecastDailyData.data != null){
        WeatherMainScreenScaffold(navController, forecastDailyData.data!!)
    }
}

@Composable
fun WeatherMainScreenScaffold(navController: NavController, data: WeatherModelResponse) {
    Scaffold(
        containerColor = colorResource(R.color.gray),
        topBar = {
            WeatherAppBar(title = "${data.city?.name!!}, ${data.city.country}",
                icon = Icons.AutoMirrored.Filled.ArrowBack ,
                navController = navController,
                elevation = 4.dp,
                onAddActionClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                },
                onButtonClicked = {
                    navController.popBackStack()
                })
        }){
        WeatherMainScreenScaffoldContent(data, it)
    }
}

@Composable
fun WeatherMainScreenScaffoldContent(data: WeatherModelResponse, padding: PaddingValues) {
    Column(
        modifier = Modifier.padding(padding).fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = formatDate(data.list[0].dt!!),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = colorResource(R.color.black)
            )
        )

        Surface(modifier = Modifier.size(200.dp).padding(16.dp),
            shape = CircleShape,
            color = colorResource(R.color.teal)
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    modifier = Modifier.size(80.dp),
                    model = BuildConfig.IMAGE_BASE_URL+ data.list[0].weather!![0]?.icon + ".png",
                    contentDescription = null
                )
                Text(
                    formatDecimals(data.list[0].temp?.day!!) + "\u00B0",
                    modifier = Modifier.padding(bottom = 4.dp),
                    style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                ))
                Text(
                    data.list[0].weather?.get(0)?.main!!, style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                )
            }
        }
        HumidityWindPressureRow(data.list[0])
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        TimeRow(data.list[0])
        WeatherMainScreenColumnList(data)

    }
}

@Composable
fun WeatherMainScreenColumnList(data: WeatherModelResponse){
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("This Week", style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = colorResource(R.color.navy)
        ))
        Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(vertical = 8.dp),
            color = Color.LightGray,
            shape = RoundedCornerShape(16.dp)
        ){
            LazyColumn(modifier = Modifier.padding(vertical = 8.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list){ item->
                    WeatherWeekListItem(item)
                }
            }
        }

    }
}

@Composable
fun TimeRow(data: ListItem?){
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(R.drawable.ic_sunrise), contentDescription = "sunrise",
                modifier = Modifier.size(20.dp),
                tint = colorResource(R.color.teal)
            )
            Text(
                formatDateTime(data?.sunrise!!), style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = colorResource(R.color.navy)
            )
            )

        }

        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(R.drawable.ic_sunset), contentDescription = "sunset",
                modifier = Modifier.size(20.dp),
                tint = colorResource(R.color.teal)
            )
            Text(
                formatDateTime(data?.sunset!!), style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = colorResource(R.color.navy)
                )
            )

        }
    }
}

@Composable
fun HumidityWindPressureRow(data: ListItem?) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(R.drawable.ic_humidity), contentDescription = "humidity",
                modifier = Modifier.size(20.dp),
                tint = colorResource(R.color.teal)
            )
            Text(data?.humidity.toString()+"%", style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = colorResource(R.color.navy)
            )
            )
        }

        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(R.drawable.ic_pressure), contentDescription = "pressure",
                modifier = Modifier.size(30.dp),
                tint = colorResource(R.color.teal)
            )
            Text(data?.pressure.toString()+"psi", style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = colorResource(R.color.navy)
            )
            )
        }

        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(R.drawable.ic_wind), contentDescription = "wind",
                modifier = Modifier.size(30.dp),
                tint = colorResource(R.color.teal)
            )
            Text(
                formatDecimals(data?.speed!!)+"mph", style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = colorResource(R.color.navy)
            )
            )
        }
    }
}
