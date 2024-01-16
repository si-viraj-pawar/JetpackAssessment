package com.example.jetpackassessment.framework.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jetpackassessment.R
import com.example.jetpackassessment.framework.model.X3632
import com.example.jetpackassessment.framework.vm.MatchVm

class TeamListActivity : ComponentActivity() {
    var teamList = ArrayList<X3632>()
    var teamName = ""
    var vm = MatchVm()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamList = intent.getSerializableExtra("TeamModel") as ArrayList<X3632>
        teamName = intent.getStringExtra("TeamName")!!
        Log.d("TEAM_LIST", "onCreate: " + teamList)

       /**/
        setContent {
            Greeting(teamList, teamName)
            vm.playerDetails.observe(this){
               // customDialog(it)
            }
        }
    }
}

//list: List<X3632>, teamName: String
@Composable
fun Greeting(list: ArrayList<X3632>, teamName: String) {
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            Text(
                text = teamName,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box {
            LazyColumn {
                items(list) { players ->
                    playerListView(players = players)
                }
            }
        }
    }
}

@Composable
fun playerListView(players : X3632){
    var isDialogOpen = remember { mutableStateOf(true) }
    var isOpen = false
    Card (modifier = Modifier
        .fillMaxHeight(0.1f)
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            isDialogOpen.value = true
          /*  if (isOpen) {
                isOpen = false
            }else{
                isOpen = true
            }*/
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ){
        Row {
            when{
                isDialogOpen.value -> {
                    CustomDialog(
                        players,
                        onDismissReq = { isDialogOpen.value = false }
                    )
                }
            }
        }

        Row{
            Image(
                painter = painterResource(id = R.drawable.playericon),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
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


/*
@Composable
fun customDialog(

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
                .fillMaxHeight(0.1f)
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
            Image(
                painter = painterResource(id = R.drawable.playericon),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .width(50.dp)
            )
            Column (
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(5.dp))
            {
                */
/*  *//*

                Column {
                    Text(text = "Balling",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )


                }

                Column {
                    Text(text = "Balling",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )


                }


            }
        }
    }
}
*/

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    // Greeting()
    //Greeting(list = teamList,"India")
    //Greeting(list = , teamName = )
}