package com.nadim.atlaspackaging.archive_feature.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nadim.atlaspackaging.R

@Composable
fun ProductionTable(
    date: String ,
    client: String ,
    article: String ,
    post: String ,
    conductor: String ,
    commentary: String ,
    lot: String ,
    secondaryLotNumber: String ,
    production: String ,
    waste: String,
    time: String,
    onIconClick: () -> Unit,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        backgroundColor = Color.LightGray
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
                    ) {
                IconButton(onClick = onIconClick) {
                    Image(
                        modifier = Modifier
                            .size(30.dp)
                            .background(color = Color.Red, shape = RoundedCornerShape(5.dp)),
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Close"
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Archive Daily Production Form:",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(40.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "date:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = date)
                }
                Row {
                    Text(text = "conductor:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = conductor)
                }

                Row {
                    Text(text = "post:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = post)
                }

                Row {
                    Text(text = "client:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = client)
                }
                Row {
                    Text(text = "article:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = article)
                }

                Row {
                    Text(text = "lot:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "AP$lot - $secondaryLotNumber")
                }
                Row {
                    Text(text = "production:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = production)
                }
                Row {
                    Text(text = "waste:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = waste)
                }
                Row {
                    Text(text = "commentary:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = commentary)
                }
                Row {
                    Text(text = "time:")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = time)
                }
            }
        }
    }
}