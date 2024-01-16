package com.example.jetpackassessment.framework.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackassessment.framework.model.MatchDetailModel
import com.example.jetpackassessment.framework.model.X3632
import com.example.jetpackassessment.framework.repo.MatchRepo
import com.example.jetpackassessment.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchVm : ViewModel() {

    private val _matchDetails = MutableLiveData<MatchDetailModel>()
    val matchDetails : LiveData<MatchDetailModel> get() = _matchDetails

    var _playerDetails = MutableLiveData<X3632>()
    val playerDetails : LiveData<X3632> get() = _playerDetails

    private val _err = MutableLiveData<String>()
    private val err : LiveData<String> get() = _err

    var matchRepo: MatchRepo = MatchRepo()

    init {
        getMatchDetails()
    }

    fun getMatchDetails() {
        var matchDetails = ApiConfig.getApiService().getMatchDetails()

        matchDetails.enqueue(object : Callback<MatchDetailModel> {
            override fun onResponse(
                call: Call<MatchDetailModel>,
                response: Response<MatchDetailModel>
            ) {
                if (!response.isSuccessful || response.body() == null){
                    errMsg("Something went wrong")
                    return
                }
                _matchDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<MatchDetailModel>, t: Throwable) {
                errMsg(t.message)
            }

        })
    }

    fun errMsg(err : String?){
        if(!err!!.isBlank()){
            _err.value = err
        }
    }
}