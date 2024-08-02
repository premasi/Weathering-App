package com.rakarguntara.weatheringapp.widgets

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rakarguntara.weatheringapp.R

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation : Dp = 8.dp,
//    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.brown),
            titleContentColor = colorResource(R.color.sage)
        ),
        title = {
            Text(title, style = TextStyle(
                fontSize = 24.sp,
                color = colorResource(R.color.sage),
                fontWeight = FontWeight.SemiBold
            ))
        }
    )
}