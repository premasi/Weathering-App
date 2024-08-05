package com.rakarguntara.weatheringapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rakarguntara.weatheringapp.R

@Composable
fun SearchBarComponent(
    onSearch: (String) -> Unit = {}
){
    val searchQueryState = rememberSaveable{
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }

    Column {
        CommonTextField(
            valueState = searchQueryState,
            placeHolder = "City",
            onAction = KeyboardActions {
                if(!valid){
                    return@KeyboardActions
                } else{
                    onSearch(searchQueryState.value.trim())
                    searchQueryState.value = ""
                    keyboardController?.hide()
                }
            }
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(valueState: MutableState<String>,
                    placeHolder: String,
                    onAction: KeyboardActions = KeyboardActions.Default,
                    keyboardType: KeyboardType = KeyboardType.Text,
                    imeAction: ImeAction = ImeAction.Next) {
    OutlinedTextField(value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(placeHolder, style = TextStyle(
            color = colorResource(R.color.black),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedLabelColor = Color.LightGray,
            focusedBorderColor = colorResource(R.color.navy),
            focusedTextColor = colorResource(R.color.black),
            cursorColor = Color.Black,
            unfocusedLabelColor = colorResource(R.color.navy),
            unfocusedBorderColor = Color.LightGray,
            unfocusedTextColor = Color.Black
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    )

}
