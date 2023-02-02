package com.nadim.atlaspackaging.main_feature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadim.atlaspackaging.R

@Composable
fun SectionItem(
    machine: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .padding(10.dp),
        elevation = 15.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Image(
                modifier = Modifier.height(120.dp),
                painter = painterResource(id = R.drawable.atlas),
                contentDescription = "Background",
                contentScale = ContentScale.Fit,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Black.copy(alpha = .3f)),
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    text = "Suivie de production $machine", style = MaterialTheme.typography.h5,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun SectionItemPreview() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SectionItem(machine = "Flexo") {
            }
            SectionItem(machine = "Decoupe1") {
            }
            SectionItem(machine = "Sealer") {
            }
        }

    }
}