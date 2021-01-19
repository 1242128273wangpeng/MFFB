package com.wangpeng.firstfirebase.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangpeng.firstfirebase.domain.model.DetailInfoModel
import com.wangpeng.firstfirebase.domain.usecase.GetDetailUserCase
import com.wangpeng.firstfirebase.utils.state.Result
import kotlinx.coroutines.launch

class DetailViewModel(private val getDetailUserCase: GetDetailUserCase) : ViewModel() {
    private var detailInfoMuLiveData = MutableLiveData<Result<DetailInfoModel>>()
    val detailInfoLiveData: LiveData<Result<DetailInfoModel>> = detailInfoMuLiveData

    fun getDetaliDouBan(id: String) {
        viewModelScope.launch {
            try {
                getDetailUserCase.execute(id).let {
                    detailInfoMuLiveData.postValue(Result.Success(it?:DetailInfoModel()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                detailInfoMuLiveData.postValue(Result.Error())
            }
        }
    }
}