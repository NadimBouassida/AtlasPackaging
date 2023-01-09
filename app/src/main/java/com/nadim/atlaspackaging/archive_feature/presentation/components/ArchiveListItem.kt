package com.nadim.atlaspackaging.archive_feature.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nadim.atlaspackaging.R

@Composable
fun ArchiveListItem(
    date: String,
    article: String,
    client: String,
    onClick: () -> Unit,
    onIconClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .clickable { onClick() }
        ,
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        backgroundColor = Color.LightGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
            ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                Column (
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
                        .weight(1f)
                        )  {
                    Text(text = "Date:")
                    Text(text = "Client:")
                    Text(text = "Article:")
                }
                Column (
                    modifier = Modifier.padding(end = 30.dp, top = 10.dp, bottom = 10.dp)
                        .weight(1.5f)
                ) {
                    Text(text = date)
                    Text(text = client)
                    Text(text = article)
                }
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 15.dp, top = 20.dp)
                        .weight(.3f)
                    ,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End
                    ){
                        IconButton(modifier = Modifier.size(40.dp),onClick = { onIconClick() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_delete),
                                contentDescription = "delete",
                            )
                        }
                    }
                }
            }
        }
}

