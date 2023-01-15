package com.nadim.atlaspackaging.daily_production.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadim.atlaspackaging.R

@Composable
fun ProdItemText(
    showErrorEmptyField: Boolean,
    text: String,
    modifier: Modifier = Modifier,
    showTrailingIcon:Boolean ,
    enabled: Boolean ,
    numbersOnly : Boolean = false,
    maxLines: Int ,
    onClick: () -> Unit = {},
    onClickOne: () -> Unit = {},
    onClickTwo: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
){
    TextField(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
                onClickOne()
                onClickTwo()
            },
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = MaterialTheme.typography.h6,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            disabledTextColor = Color.Black
        ),
        maxLines = maxLines,
        trailingIcon = {
            if (showTrailingIcon) {
                IconButton(onClick = {
                    onClick()
                    onClickOne()
                    onClickTwo()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_down_arrow),
                        contentDescription = "Down Arrow"
                    )
                }
            }
        },
        isError = showErrorEmptyField,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (numbersOnly ){KeyboardType.Number} else {
                KeyboardType.Ascii}
        )
    )
}

@Preview
@Composable
fun ProdItemTextPreview(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ){
        ProdItemText(
            showErrorEmptyField = false,
            text = "",
            showTrailingIcon = true,
            enabled = true,
            maxLines = 1
        )
    }
}
