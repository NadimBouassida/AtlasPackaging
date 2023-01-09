package com.nadim.atlaspackaging.login_feature.presentation

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

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

