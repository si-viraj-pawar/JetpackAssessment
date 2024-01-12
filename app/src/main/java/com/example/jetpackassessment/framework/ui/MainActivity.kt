package com.example.jetpackassessment.framework.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackassessment.R
import com.example.jetpackassessment.framework.vm.MatchVm
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    var matchVm =  MatchVm()
    var matchDate : String = ""
    var matchTime : String = ""
    var matchVenue : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            previewFun()
        }
    }



    @Composable
    fun homeScreen(){

      //  val matchDetails = matchVm.matchDetails.collectAsStateWithLifecycle.value?.Matchdetail
        val matchDetails = matchVm.matchDetails.observeAsState().value
        matchDate = matchDetails?.Matchdetail?.Match?.Date?: ""
        matchTime = matchDetails?.Matchdetail?.Match?.Time?: ""
        matchVenue = matchDetails?.Matchdetail?.Venue?.Name?: ""
        var teamName1 = ""
        var teamName2 = ""
        val stringBuilder = StringBuilder()
        if (matchDetails != null) {
            for (i in matchDetails.Teams){
                val key = i.key
                val teamName = matchDetails.Teams[key]?.Name_Full
                stringBuilder.append(teamName).append("\nvs\n").toString()
            }
        }
        if (stringBuilder.isNotEmpty()){
            val teamNameData = stringBuilder.substring(0,stringBuilder.length-4)
            teamName1 = teamNameData.split("vs").get(0)
            teamName2 = teamNameData.split("vs").get(1)
            Log.d("TEAM_DATA", "onCreate: "+teamName1)
        }

        Image(
                painter = painterResource(id = R.drawable.indvsnz),
                contentDescription = "Team Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

           Column (
               modifier = Modifier
                   .fillMaxWidth()
                   .height(300.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Top,
           ){
               Text(
                   text = "${matchDetails?.Matchdetail?.Match?.Date }$matchTime \n $matchVenue \n ",
                   fontSize = 14.sp,
                   color = Color.White,
                   fontStyle = FontStyle.Normal,
                   textAlign = TextAlign.Center,
                   fontWeight = FontWeight.Medium,
                   modifier = Modifier.padding(20.dp, 50.dp
                   )
               )

               Row (modifier = Modifier
                   .fillMaxWidth()
                   .weight(1f)
                   .padding(20.dp),
                   verticalAlignment = Alignment.Bottom,
                   horizontalArrangement = Arrangement.SpaceAround,
                   ){
                   Button(onClick = {
                       if (matchDetails != null) {
                           for (i in matchDetails.Teams){
                               val key = i.key
                               val teamName = matchDetails.Teams[key]?.Name_Full
                               if (teamName.equals(teamName1.trim())){
                                   val myList = ArrayList(matchDetails.Teams[key]?.Players?.values)
                                   startActivity(Intent(
                                       this@MainActivity,TeamListActivity::class.java)
                                       .putExtra("TeamModel",myList)
                                       .putExtra("TeamName",teamName))
                                   break
                               }
                           }
                       }
                   },
                       modifier = Modifier.width(130.dp)) {
                       Text(
                           text = teamName1.trim(),
                           textAlign = TextAlign.Center
                       )
                   }

                   Button(onClick = {
                       if (matchDetails != null) {
                           for (i in matchDetails.Teams){
                               val key = i.key
                               val teamName = matchDetails.Teams[key]?.Name_Full
                               if (teamName.equals(teamName2.trim())){
                                   val myList = ArrayList(matchDetails.Teams[key]?.Players?.values)
                                   startActivity(Intent(
                                       this@MainActivity,TeamListActivity::class.java)
                                       .putExtra("TeamModel",myList)
                                       .putExtra("TeamName",teamName))
                                   break
                               }
                           }
                       }

                   }) {
                       Text(text = teamName2.trim(),
                           textAlign = TextAlign.Center
                       )
                   }
               }
           }


//        }

    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun previewFun(){
        homeScreen()
    }
}

