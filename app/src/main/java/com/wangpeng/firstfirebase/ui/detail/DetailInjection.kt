package com.wangpeng.firstfirebase.ui.detail

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detaliModule = module {
    viewModel { DetailViewModel(get()) }
}
