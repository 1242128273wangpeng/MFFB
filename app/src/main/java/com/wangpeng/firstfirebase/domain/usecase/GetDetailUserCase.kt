package com.wangpeng.firstfirebase.domain.usecase

import com.wangpeng.firstfirebase.network.repo.DouBanRepository
import com.wangpeng.firstfirebase.domain.model.DetailInfoModel
import com.wangpeng.firstfirebase.domain.model.toModel

class GetDetailUserCase(private val douBanRepository: DouBanRepository) {
    suspend fun execute(id: String): DetailInfoModel? {
       return douBanRepository.getDetailInfo(id)?.toModel()
    }
}