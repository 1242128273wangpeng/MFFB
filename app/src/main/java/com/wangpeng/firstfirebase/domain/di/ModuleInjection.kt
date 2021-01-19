package com.wangpeng.firstfirebase.domain.di

import com.wangpeng.firstfirebase.domain.usecase.CombineDouBanUserCase
import com.wangpeng.firstfirebase.domain.usecase.GetDetailUserCase
import com.wangpeng.firstfirebase.domain.usecase.GetDouBanUserCase
import com.wangpeng.firstfirebase.ui.combine.combineViewModel
import com.wangpeng.firstfirebase.ui.detail.detaliModule
import com.wangpeng.firstfirebase.ui.main.mainModule
import org.koin.dsl.module


val viewModelModules = listOf(
    mainModule,
    detaliModule,
    combineViewModel
)

val domainModuleLists = module {
    factory {
        GetDetailUserCase(get())
    }
    factory {
        GetDouBanUserCase(get())
    }
    factory {
        CombineDouBanUserCase(get())
    }
}