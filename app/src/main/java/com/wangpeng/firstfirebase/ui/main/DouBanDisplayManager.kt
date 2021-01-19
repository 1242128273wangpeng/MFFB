package com.wangpeng.firstfirebase.ui.main

import com.wangpeng.firstfirebase.domain.model.DouBanModel

class DouBanDisplayManager {
    private var lastCurrencies: List<DouBanModel>? = null

    val adapter = DouBanAdapter()

    fun displayDouBanList(douBanLists: List<DouBanModel>?) {
        lastCurrencies = douBanLists
        adapter.submitList(douBanLists)
    }
}