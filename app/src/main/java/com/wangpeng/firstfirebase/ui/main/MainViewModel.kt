package com.wangpeng.firstfirebase.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangpeng.firstfirebase.domain.model.DouBanModel
import com.wangpeng.firstfirebase.domain.usecase.GetDouBanUserCase
import com.wangpeng.firstfirebase.utils.extensions.intervalLaunch
import com.wangpeng.firstfirebase.utils.state.Result
import timber.log.Timber
import java.lang.Exception

class MainViewModel(private val getDouBanUserCase: GetDouBanUserCase) : ViewModel() {
    private val douBanMutableLiveData = MutableLiveData<Result<List<DouBanModel>>>()
    val douBanLiveData: LiveData<Result<List<DouBanModel>>> = douBanMutableLiveData

    init {
        getDouBan()
    }

    private fun getDouBan() {
        douBanMutableLiveData.postValue(Result.Loading)
        viewModelScope.intervalLaunch(GET_DOUBAN_INTERVAL_MS) {
            try {
                getDouBanUserCase.execute().let { res ->
                    douBanMutableLiveData.postValue(Result.Success(res))
                }
            } catch (e: Exception) {
                Timber.e(e)
                douBanMutableLiveData.postValue(Result.Error())
            }
        }
    }

    companion object {
        const val GET_DOUBAN_INTERVAL_MS = 300000L
    }
}