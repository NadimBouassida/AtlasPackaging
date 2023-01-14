package com.nadim.atlaspackaging.main_feature.presentation

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadim.atlaspackaging.R

@Composable
fun SectionItem(
    machine : String,
    onClick: () -> Unit,
){
    Card (
        modifier = Modifier
            .clickable { onClick() }
            .padding(5.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.LightGray,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.atlas_background),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(top = 20.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = .8f)
                            ),
                            startY = 300f
                        )
                    )
                )
            Text(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            text = machine, style = MaterialTheme.typography.body1, color = Color.White)
        }
    }
}

@Preview
@Composable
fun SectionItemPreview(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        SectionItem(machine = "") {

        }
    }
}