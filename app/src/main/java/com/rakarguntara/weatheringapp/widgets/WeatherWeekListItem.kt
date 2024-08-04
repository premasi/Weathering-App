package com.rakarguntara.weatheringapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rakarguntara.weatheringapp.BuildConfig
import com.rakarguntara.weatheringapp.R
import com.rakarguntara.weatheringapp.models.ListItem
import com.rakarguntara.weatheringapp.utils.formatDate
import com.rakarguntara.weatheringapp.utils.formatDecimals

@Composable
fun WeatherWeekListItem(data: ListItem) {
    Surface(modifier = Modifier.padding(3.dp).fillMaxWidth(),
        shape = CircleShape.copy(topStart = CornerSize(10.dp)),
        color = colorResource(R.color.blue)
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(formatDate(data.dt!!).split(",")[0], style = TextStyle(
                fontSize = 12.sp,
                color = colorResource(R.color.gray),
                fontWeight = FontWeight.Normal
            ),
                modifier = Modifier.padding(5.dp))
            AsyncImage(model = BuildConfig.IMAGE_BASE_URL+ data.weather!![0]?.icon+".png", contentDescription = "icon",
                modifier = Modifier.size(50.dp))

            Surface(
                shape = CircleShape,
                color = Color.Yellow
            ) {
                Text(data.weather[0]?.description!!,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(4.dp)
                )
            }
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Yellow,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )){
                    append(formatDecimals(data.temp?.max!!) + "\u00B0")
                }
                append(" ")
                withStyle(style = SpanStyle(
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                ){
                    append(formatDecimals(data.temp?.min!!) + "\u00B0")
                }
            }, modifier = Modifier.padding(5.dp))
        }

    }



}