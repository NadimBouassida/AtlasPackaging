package com.nadim.atlaspackaging.machine_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun MachineItem(
    text: String,
    onClick : () -> Unit,
    id: Int
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = "Add",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp, top = 2.dp)
                .size(40.dp),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            color = Color.Black
        )
    }
}

