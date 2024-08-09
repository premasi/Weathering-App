package com.rakarguntara.weatheringapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rakarguntara.weatheringapp.R
import com.rakarguntara.weatheringapp.models.Unit
import com.rakarguntara.weatheringapp.viewmodels.SettingViewModel
import com.rakarguntara.weatheringapp.widgets.WeatherAppBar

@Composable
fun WeatherSettingsScreen(navController: NavController,
                          settingViewModel: SettingViewModel = hiltViewModel()
) {
    val unitToggleState = remember {
        mutableStateOf(false)
    }
    val measurementUnits = listOf("Imperial (F)", "Metric (C)")
    val choiceFromDb = settingViewModel.unitList.collectAsState().value
    val defaultChoice = if(choiceFromDb.isEmpty()) measurementUnits[0]
    else choiceFromDb[0].unit
    val choiceState = remember {
        mutableStateOf(defaultChoice)
    }
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            isMainScreen = false,
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            navController = navController,
            onButtonClicked = {
                navController.popBackStack()
            }
        )
    }) {
        Surface(modifier = Modifier.padding(it)
            .fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text("Change Units of Measurement",
                    modifier = Modifier.padding(bottom = 16.dp))

                IconToggleButton(checked = !unitToggleState.value,
                    onCheckedChange = { bool ->
                        unitToggleState.value = !bool
                        if(unitToggleState.value){
                            choiceState.value = "Imperial (F)"
                        } else {
                            choiceState.value = "Metric (C)"
                        }
                    }, modifier = Modifier.fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(colorResource(R.color.navy))
                ){
                    Text(text = if(unitToggleState.value) "Fahrenheit" else "Celcius",
                        style = TextStyle(
                            color = colorResource(R.color.white),
                            fontSize = 12.sp
                        )
                    )

                }
                Button(onClick = {
                    settingViewModel.deleteAllUnit()
                    settingViewModel.insertUnit(Unit(
                        unit = choiceState.value
                    ))
                },
                    modifier = Modifier.padding(3.dp).align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = colorResource(R.color.blue)
                    )
                ){
                    Text(text = "Save", color = colorResource(R.color.gray),
                        fontSize = 12.sp)
                }
            }
        }
    }
}