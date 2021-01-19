package com.wangpeng.firstfirebase.network.repo

import com.wangpeng.firstfirebase.network.api.DouBanApi
import com.wangpeng.firstfirebase.domain.model.DetailInfoDto
import com.wangpeng.firstfirebase.domain.model.DouBanDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DouBanRepository(private val currencyApi: DouBanApi) {
    suspend fun getDouBans(): List<DouBanDto> {
        return withContext(Dispatchers.IO) {
            println("DouBanRepository getDouBans Thread:${Thread.currentThread().name}")
            currencyApi.getDouBanData().subjects
        }
    }

    suspend fun getDetailInfo(id: String): DetailInfoDto? {
        return withContext(Dispatchers.IO) {
            println("DouBanRepository getDetailInfo Thread:${Thread.currentThread().name}")
            currencyApi.getDouBanDetail(id)
        }
    }

    suspend fun getDouBansByFlow(): List<DouBanDto> {
        return withContext(Dispatchers.IO) {
            currencyApi.getDouBanDataByFlow().subjects
        }
    }

    suspend fun getDetailInfoByFlow(id: String): DetailInfoDto? {
        return withContext(Dispatchers.IO) {
            currencyApi.getDouBanDetailByFlow(id)
        }
    }

}