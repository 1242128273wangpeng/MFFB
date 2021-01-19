package com.wangpeng.firstfirebase.ui.combine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangpeng.firstfirebase.domain.model.DetailInfoModel
import com.wangpeng.firstfirebase.domain.model.DouBanModel
import com.wangpeng.firstfirebase.domain.usecase.CombineDouBanUserCase
import com.wangpeng.firstfirebase.utils.state.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger


class CombineViewModel(private val combineDouBanUserCase: CombineDouBanUserCase) : ViewModel() {
    private var detailInfoMuLiveData = MutableLiveData<Result<List<DetailInfoModel>>>()
    val detailInfoLiveData: LiveData<Result<List<DetailInfoModel>>> = detailInfoMuLiveData

    companion object {
        @Volatile
        var countSize = 0
        var modelResultList = mutableListOf<DetailInfoModel>()
    }

    fun getCombineDouBan() {
        viewModelScope.launch {
            try {
                combineDouBanUserCase.getListDouBan().let { lists ->
                    modelResultList.clear()

                    countSize = lists.size
                    println("Thread:${Thread.currentThread().name} lists size:${lists.size}")
                    lists.forEach { model: DouBanModel ->
                        println("id:${model.id} name:${model.title}")
                        launch(Dispatchers.IO) {
                            val item = combineDouBanUserCase.queryDetailExecute(model.id)
                            println("Thread:${Thread.currentThread().name} item:$item")
                            if (item != null) {
                                modelResultList.add(item)
                            }

                            countSize--

                            if (countSize == 0
                            ) {
                                detailInfoMuLiveData.postValue(
                                    Result.Success(
                                        modelResultList
                                    )
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                detailInfoMuLiveData.postValue(Result.Error())
            }
        }
    }

    fun getCombineDouBanMap() {
        viewModelScope.launch {
            try {
                combineDouBanUserCase.getListDouBan().let { lists ->
                    modelResultList.clear()

                    countSize = lists.size
                    println("Thread:${Thread.currentThread().name} lists size:${lists.size}")
                    lists.map { model: DouBanModel ->
                        println("id:${model.id} name:${model.title}")
                        async(Dispatchers.IO) {
                            val item = combineDouBanUserCase.queryDetailExecute(model.id)
                            println("Thread:${Thread.currentThread().name} item:$item")
                            if (item != null) {
                                modelResultList.add(item)
                            }

                            countSize--

                            if (countSize == 0
                            ) {
                                detailInfoMuLiveData.postValue(
                                    Result.Success(
                                        modelResultList
                                    )
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                detailInfoMuLiveData.postValue(Result.Error())
            }
        }
    }

    fun getCombineDouBanFlatten() {
        viewModelScope.launch {
            try {
                combineDouBanUserCase.getListDouBan().let { lists ->
                    modelResultList.clear()
                    println("Thread:${Thread.currentThread().name} lists size:${lists.size}")
                    lists.map { model: DouBanModel ->
                        println("id:${model.id} name:${model.title}")
                        async(Dispatchers.IO) {
                            val item = combineDouBanUserCase.queryDetailExecute(model.id)
                            println("Thread:${Thread.currentThread().name} item:$item")
                            item?.takeIf {
                                modelResultList.add(it)
                            }
                        }
                    }.awaitAll().asFlow().collect {
                        detailInfoMuLiveData.postValue(
                            Result.Success(
                                modelResultList
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                detailInfoMuLiveData.postValue(Result.Error())
            }
        }
    }

    @FlowPreview
    fun getCombineDouBanFlow() {
        try {
            viewModelScope.launch {
                combineDouBanUserCase.getListDouBanByFlow()
                    .asFlow()
                    .flatMapMerge {
                        flow {
                            emit(combineDouBanUserCase.queryDetailExecuteByFlow(it.id))
                            println("flatMapMerge emit:${it.id}")
                        }
                    }
                    .collect { println("collect $it") }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            detailInfoMuLiveData.postValue(Result.Error())
        }
    }
}