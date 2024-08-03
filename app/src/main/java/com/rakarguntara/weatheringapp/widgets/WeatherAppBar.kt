package com.rakarguntara.weatheringapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rakarguntara.weatheringapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation : Dp = 8.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.navy),
            titleContentColor = colorResource(R.color.gray)
        ),
        title = {
            Text(title, style = TextStyle(
                fontSize = 24.sp,
                color = colorResource(R.color.gray),
                fontWeight = FontWeight.SemiBold
            ))
        },
        actions = {
            if(isMainScreen){
                IconButton(onClick = {}){
                    Icon(Icons.Default.Search, contentDescription = "Search Icon", tint = colorResource(R.color.gray))
                }

                IconButton(onClick = {}){
                    Icon(Icons.Rounded.MoreVert, contentDescription = "More Info", tint = colorResource(R.color.gray))
                }
            } else {
                Box {  }
            }
        },
        navigationIcon = {
            if(icon != null){
                if(isMainScreen){
                    Box{}
                } else {
                    Icon(imageVector = icon, contentDescription = null, tint = colorResource(R.color.gray),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable {
                                onButtonClicked.invoke()
                            })
                }

            }
        }
    )
}