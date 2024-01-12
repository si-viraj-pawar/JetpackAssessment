package com.example.jetpackassessment.framework.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackassessment.framework.model.MatchDetailModel
import com.example.jetpackassessment.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepo {

    private var matchDetailsLiveData = MutableLiveData<MatchDetailModel>()
    private var errorMsg = MutableLiveData<String>()

    fun getMatchDetails() : LiveData<MatchDetailModel> {
        var matchDetails = ApiConfig.getApiService().getMatchDetails()

        matchDetails.enqueue(object : Callback<MatchDetailModel>{
            override fun onResponse(
                call: Call<MatchDetailModel>,
                response: Response<MatchDetailModel>
            ) {
                if (!response.isSuccessful || response.body() == null){
                    errMsg("Something went wrong")
                    return
                }

                matchDetailsLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MatchDetailModel>, t: Throwable) {
                errMsg(t.message)
            }

        })
        return matchDetailsLiveData
    }

    fun errMsg(err : String?){
       if(!err!!.isBlank()){
           errorMsg.value = err
        }
    }
}