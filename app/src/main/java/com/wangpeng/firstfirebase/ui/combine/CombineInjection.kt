package com.wangpeng.firstfirebase.ui.combine

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val combineViewModel = module {
    viewModel { CombineViewModel(get()) }
}
