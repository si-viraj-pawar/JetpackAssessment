package com.example.jetpackassessment.framework.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackassessment.R
import com.example.jetpackassessment.framework.model.X3632


@Composable
fun playerListView(players : X3632){
    Card (modifier = Modifier
        .fillMaxHeight(0.1f)
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {  },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ){
        Row{
            Image(
                painter = painterResource(id = R.drawable.playericon),
                contentDescription = "",
                modifier = Modifier.fillMaxHeight().width(100.dp)
            )
            Column (modifier = Modifier.padding(20.dp)) {
                Text(
                    text = players.Name_Full,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 18.sp
                )

                    if (players.IsCaptain) {
                        Text(
                            text = "Captain",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Green,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.fillMaxWidth()
                            )
                    }
                    if (players.Iskeeper) {
                        Text(
                            text = "Keeper",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Yellow,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }



