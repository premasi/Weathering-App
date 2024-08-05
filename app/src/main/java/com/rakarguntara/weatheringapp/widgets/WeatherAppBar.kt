package com.rakarguntara.weatheringapp.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.rakarguntara.weatheringapp.navigation.WeatherScreens

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
    val showDialogState = remember {
        mutableStateOf(false)
    }

    if(showDialogState.value){
        showSettingDialogMenu(showDialogState = showDialogState, navController = navController)
    }

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
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }){
                    Icon(Icons.Default.Search, contentDescription = "Search Icon", tint = colorResource(R.color.gray))
                }

                IconButton(onClick = {
                    showDialogState.value = !showDialogState.value
                    Log.d("Dialog State", "WeatherAppBar: ${showDialogState.value}")
                }){
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

@Composable
fun showSettingDialogMenu(showDialogState: MutableState<Boolean>, navController: NavController) {
    val expanded = remember {
        mutableStateOf(true)
    }
    val menuItem = listOf<String>(
        "Favorites",
        "About",
        "Settings",
    )
    Column(
        modifier = Modifier.fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(right = 16.dp, top = 80.dp)
    ) {
        DropdownMenu(
            modifier = Modifier.width(150.dp)
                .background(color = colorResource(R.color.gray)),
            expanded = expanded.value,
            onDismissRequest = {expanded.value = false}
        ){
            menuItem.forEachIndexed{ index, text ->
                DropdownMenuItem(
                    interactionSource = MutableInteractionSource(),
                    text = {
                        Row(
                            modifier = Modifier.fillMaxSize()
                                .padding(8.dp)
                                .clickable {
                                    navController.navigate(
                                        when(text){
                                            "About" -> WeatherScreens.AboutScreen.name
                                            "Favorites" -> WeatherScreens.FavoriteScreen.name
                                            else -> WeatherScreens.SettingsScreen.name
                                        }
                                    )
                                }
                            ,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                imageVector = when(text){
                                    "About" -> Icons.Default.Info
                                    "Favorites" -> Icons.Default.Favorite
                                    else -> Icons.Default.Settings
                                },
                                contentDescription = null,
                                tint = colorResource(R.color.teal)
                            )
                            Text(text, style = TextStyle(
                                color = colorResource(R.color.black),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal
                            )
                            )
                        }
                    },
                    onClick = {
                    expanded.value = false
                    showDialogState.value = false
                    }
                )
            }
        }
    }

}
