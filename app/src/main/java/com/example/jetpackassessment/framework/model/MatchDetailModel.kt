package com.example.jetpackassessment.framework.model

import java.io.Serializable

data class MatchDetailModel(
    val Innings: List<Inning>,
    val Matchdetail: Matchdetail,
    val Notes: Notes,
    val Nuggets: List<String>,
    val Teams: HashMap<String,X4>
):Serializable