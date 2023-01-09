package com.nadim.atlaspackaging.login_feature.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadim.atlaspackaging.R


@Composable
fun LoginScreen(
    navController: NavController,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Atlas Packaging",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.h5
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 20.dp),
            backgroundColor = Color.LightGray,
            elevation = 10.dp,
            border = BorderStroke(width = 3.dp, color = MaterialTheme.colors.primary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.atlas),
                    contentDescription = "logo",
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .size(160.dp)
                        .border(
                            width = 5.dp,
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.secondary.copy(alpha = 0.2f)),
                )
                Credentials(
                    navController = navController
                )
            }
        }
    }
}