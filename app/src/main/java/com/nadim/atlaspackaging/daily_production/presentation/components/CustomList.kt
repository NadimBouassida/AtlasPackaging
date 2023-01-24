package com.nadim.atlaspackaging.daily_production.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.nadim.atlaspackaging.R

@Composable
fun CustomList(
    onIconClick: () -> Unit,
    onItemClick: (String) -> Unit,
    itemsList: List<String?>,
    offset: IntOffset = IntOffset(0,0)
){
    Column (
        modifier = Modifier.absoluteOffset { offset }
        .fillMaxWidth(.7f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(modifier = Modifier
            .align(Alignment.End)
            .size(30.dp),
            onClick = onIconClick) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Red, shape = RoundedCornerShape(5.dp)),
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "Close"
            )
        }
        LazyColumn {
            items(itemsList) {
                ListItem(
                    text = it.toString(),
                    onClick = {
                        onItemClick(it.toString())
                    },
                )
            }
        }
    }
}


@Preview
@Composable
fun CustomListPreview(){
    val list = listOf("item one", "item2", "item3")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CustomList(onIconClick = {}, onItemClick = {}, itemsList = list)
    }
}