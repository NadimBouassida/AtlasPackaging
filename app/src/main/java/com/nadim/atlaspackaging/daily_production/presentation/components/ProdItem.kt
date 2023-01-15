package com.nadim.atlaspackaging.daily_production.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



@Composable
fun ProdItem(
    order: Int,
    label:String,
    text: String,
    showErrorEmptyField: Boolean = false,
    showTrailingIcon:Boolean = false,
    numbersOnly: Boolean = false,
    enabled: Boolean = true,
    maxLines: Int = 1,
    onClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {

    Card(
        Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
                    .padding(start = 20.dp, top = 8.dp),
                text = "$order. $label",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.background
            )

            ProdItemText(
                text = text,
                onClick = { onClick() },
                onValueChange = onValueChange,
                showTrailingIcon = showTrailingIcon,
                showErrorEmptyField = showErrorEmptyField,
                enabled = enabled,
                numbersOnly = numbersOnly,
                maxLines = maxLines,
            )

            val backgroundColor = if (showErrorEmptyField) {
                Color.Red
            } else Color.LightGray

            val textColor = if (showErrorEmptyField) {
                Color.White
            } else Color.Black

            val errorText = if (showErrorEmptyField) {
                "This field requires an answer"
            } else "Required"

            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(backgroundColor)
            )
            {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = errorText,
                    color = textColor,
                )
            }
        }
    }
}