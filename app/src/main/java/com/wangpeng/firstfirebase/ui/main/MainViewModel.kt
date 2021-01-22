package com.wangpeng.firstfirebase.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangpeng.firstfirebase.domain.model.DouBanModel
import com.wangpeng.firstfirebase.domain.usecase.GetDouBanUserCase
import com.wangpeng.lib_net.extensions.intervalLaunch
import com.wangpeng.lib_net.state.RequestResult
import timber.log.Timber
import java.lang.Exception

class MainViewModel(private val getDouBanUserCase: GetDouBanUserCase) : ViewModel() {
    private val douBanMutableLiveData = MutableLiveData<RequestResult<List<DouBanModel>>>()
    val douBanLiveData: LiveData<RequestResult<List<DouBanModel>>> = douBanMutableLiveData

    init {
        intervalGetDouBan()
    }

    private fun intervalGetDouBan() {
        douBanMutableLiveData.postValue(RequestResult.Loading)
        viewModelScope.intervalLaunch(GET_INTERVAL_MS) {
            try {
                getDouBanUserCase.execute().let { res ->
                    douBanMutableLiveData.postValue(RequestResult.Success(res))
                }
            } catch (e: Exception) {
                Timber.e(e)
                douBanMutableLiveData.postValue(RequestResult.Error())
            }
        }
    }

    companion object {
        const val GET_INTERVAL_MS = 300000L
    }
}