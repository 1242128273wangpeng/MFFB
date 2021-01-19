package com.wangpeng.firstfirebase.domain.usecase

import com.wangpeng.firstfirebase.network.repo.DouBanRepository
import com.wangpeng.firstfirebase.domain.model.DouBanModel
import com.wangpeng.firstfirebase.domain.model.toModel

class GetDouBanUserCase(private val douBanRepository: DouBanRepository) {
    suspend fun execute(): List<DouBanModel> = douBanRepository.getDouBans().map { it.toModel() }
}