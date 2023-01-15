package com.nadim.atlaspackaging.daily_production.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(
    text: String,
    onClick: () -> Unit,
){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.primaryVariant.copy())
            .border(1.dp, color = Color.White)
            .padding(10.dp)
            .clickable { onClick() },
        text = text,
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        color = Color.White,
    )
}

@Preview
@Composable
fun MyCustomListItem() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        ListItem(text = "SomeText", onClick = {  })
    }
}

