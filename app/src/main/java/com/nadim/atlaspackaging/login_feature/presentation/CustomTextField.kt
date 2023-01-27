package com.nadim.atlaspackaging.login_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomTextField(
    text: String,
    onValueChange: (string : String) -> Unit = {},
    onError: Boolean = false,
    label: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon : @Composable () -> Unit = {},
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        isError = onError,
        label = {Text(text = label, style = MaterialTheme.typography.body1)},
        placeholder =  {Text(text = label,style = MaterialTheme.typography.body1)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        trailingIcon = {trailingIcon()}
    )
}

@Preview()
@Composable
fun CustomTextFieldPreview(){
    Box(modifier = Modifier.background(Color.White)){
        CustomTextField(text = "Email", label = "Email", keyboardType = KeyboardType.Email)
    }
}

