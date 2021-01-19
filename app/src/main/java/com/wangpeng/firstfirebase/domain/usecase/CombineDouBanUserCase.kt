package com.wangpeng.firstfirebase.domain.usecase

import com.wangpeng.firstfirebase.domain.model.DetailInfoModel
import com.wangpeng.firstfirebase.network.repo.DouBanRepository
import com.wangpeng.firstfirebase.domain.model.DouBanModel
import com.wangpeng.firstfirebase.domain.model.toModel

class CombineDouBanUserCase(private val douBanRepository: DouBanRepository) {
    suspend fun getListDouBan(): List<DouBanModel> =
        douBanRepository.getDouBans().map { it.toModel() }

    suspend fun queryDetailExecute(id: String): DetailInfoModel? =
        douBanRepository.getDetailInfo(id)?.toModel()

    suspend fun getListDouBanByFlow(): List<DouBanModel> =
        douBanRepository.getDouBansByFlow().map { it.toModel() }

    suspend fun queryDetailExecuteByFlow(id: String): DetailInfoModel? =
        douBanRepository.getDetailInfoByFlow(id)?.toModel()

}