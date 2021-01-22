package com.wangpeng.firstfirebase.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangpeng.firstfirebase.domain.model.DetailInfoModel
import com.wangpeng.firstfirebase.domain.usecase.GetDetailUserCase
import com.wangpeng.lib_net.state.RequestResult
import kotlinx.coroutines.launch

class DetailViewModel(private val getDetailUserCase: GetDetailUserCase) : ViewModel() {
    private var detailInfoMuLiveData = MutableLiveData<RequestResult<DetailInfoModel>>()
    val detailInfoLiveData: LiveData<RequestResult<DetailInfoModel>> = detailInfoMuLiveData

    fun getDetailDouBan(id: String) {
        viewModelScope.launch {
            try {
                getDetailUserCase.execute(id).let {
                    detailInfoMuLiveData.postValue(RequestResult.Success(it?:DetailInfoModel()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                detailInfoMuLiveData.postValue(RequestResult.Error())
            }
        }
    }
}