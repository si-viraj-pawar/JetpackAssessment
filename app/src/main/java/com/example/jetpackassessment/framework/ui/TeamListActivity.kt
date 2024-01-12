package com.example.jetpackassessment.framework.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackassessment.framework.model.Players
import com.example.jetpackassessment.framework.model.X3632

class TeamListActivity : ComponentActivity() {
    var teamList = ArrayList<X3632>()
    var teamName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamList = intent.getSerializableExtra("TeamModel") as ArrayList<X3632>
        teamName = intent.getStringExtra("TeamName")!!
        Log.d("TEAM_LIST", "onCreate: " + teamList)
        setContent {
            Greeting(teamList, teamName)
        }
    }
}

//list: List<X3632>, teamName: String
@Composable
fun Greeting(list: ArrayList<X3632>, teamName: String) {
    Column {
        Box(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    // Greeting()
    //Greeting(list = teamList,"India")
    //Greeting(list = , teamName = )
}