package com.example.jetpackassessment.network

import com.example.jetpackassessment.framework.model.MatchDetailModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("nzin01312019187360.json")
    fun getMatchDetails() : Call<MatchDetailModel>
}