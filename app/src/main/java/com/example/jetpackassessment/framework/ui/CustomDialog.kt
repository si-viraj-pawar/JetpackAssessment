package com.example.jetpackassessment.framework.ui

import android.graphics.fonts.FontStyle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jetpackassessment.R
import com.example.jetpackassessment.framework.model.X3632
import com.example.jetpackassessment.framework.vm.MatchVm

/*

*/
@Composable
fun CustomDialog(
    player: X3632,
    showDialog : Boolean,
){
    Dialog(
        onDismissRequest ={
            false
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ){
            Column (verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(5.dp)
                    .fillMaxWidth())
            {
                Image(
                    painter = painterResource(id = R.drawable.playericon),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    alignment = Alignment.Center)
                Divider(color = Color.Blue, thickness = 1.dp)
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "Batting Ratio",
                        fontWeight = FontWeight.Bold)

                    Divider(color = Color.Blue, thickness = 1.dp,
                        modifier = Modifier
                            .height(20.dp)
                            .width(0.4.dp))

                    Text(text = "Avg : ${player?.Batting?.Average}",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)

                    Text(text = "Strike Rate : ${player?.Batting?.Strikerate}",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)

                    Text(text = "Runs : ${player?.Batting?.Runs}",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)

                }
                Divider(color = Color.Blue, thickness = 1.dp)
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                    Text(text = "Balling Ratio",fontWeight = FontWeight.Bold)

                    Divider(color = Color.Blue, thickness = 1.dp,
                        modifier = Modifier
                            .height(20.dp)
                            .width(0.4.dp))

                    Text(text = "Average : ${player?.Bowling?.Average}",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)

                    Text(text = "Economy Rate : ${player?.Bowling?.Economyrate}",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)

                    Text(text = "Wickets : ${player?.Bowling?.Wickets}",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }
                Divider(color = Color.Blue, thickness = 1.dp)
            }
        }
    }
}