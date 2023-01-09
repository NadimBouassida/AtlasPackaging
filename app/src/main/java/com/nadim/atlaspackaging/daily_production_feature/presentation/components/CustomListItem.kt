package com.nadim.atlaspackaging.daily_production_feature.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomListItem(
    text: String,
    onClick: () -> Unit,
    horizontalPadding: Dp = 10.dp
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp)
                .fillMaxWidth()
                .clickable { onClick() },
            elevation = 5.dp,
            backgroundColor = Color.DarkGray,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                color = MaterialTheme.colors.primary,
                width = 2.dp
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = text,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}