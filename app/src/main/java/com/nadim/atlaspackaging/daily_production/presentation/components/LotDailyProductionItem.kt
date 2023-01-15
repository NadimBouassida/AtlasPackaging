package com.nadim.atlaspackaging.daily_production.presentation.components

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun LotDailyProductionItem(
    order: Int,
    label:String,
    textOne: String,
    textTwo: String,
    showErrorEmptyField: Boolean = false,
    showTrailingIcon:Boolean = false,
    enabled: Boolean = true,
    maxLines: Int = 1,
    onClickOne: () -> Unit = {},
    onClickTwo: () -> Unit = {},
    onValueChangeOne: (String) -> Unit = {},
    onValueChangeTwo: (String) -> Unit = {},
) {

    Card(
        modifier = Modifier
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
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.background
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    text = "AP:",
                    style = MaterialTheme.typography.h5,
                )
                ProdItemText(
                    modifier = Modifier
                        .clickable { onClickOne() }
                        .weight(2f),
                    text = textOne,
                    onValueChange = onValueChangeOne,
                    showTrailingIcon = showTrailingIcon,
                    showErrorEmptyField = showErrorEmptyField,
                    enabled = enabled,
                    maxLines = maxLines,
                    onClickOne = onClickOne,
                    numbersOnly = true
                )

                ProdItemText(
                    modifier = Modifier
                        .weight(2f)
                        .clickable { onClickTwo() },
                    text = textTwo,
                    onValueChange = onValueChangeTwo,
                    showTrailingIcon = showTrailingIcon,
                    showErrorEmptyField = showErrorEmptyField,
                    enabled = enabled,
                    maxLines = maxLines,
                    onClickTwo = onClickTwo,
                    numbersOnly = true
                )
            }

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