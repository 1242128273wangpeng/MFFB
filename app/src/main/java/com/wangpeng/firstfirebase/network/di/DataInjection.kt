package com.wangpeng.firstfirebase.network.di

import com.wangpeng.firstfirebase.network.api.DouBanApi
import com.wangpeng.firstfirebase.network.converters.NullOnEmptyConverterFactory
import com.wangpeng.firstfirebase.network.repo.DouBanRepository
import com.wangpeng.firstfirebase.network.retroflow.RetroFlowCallAdapterFactory
import com.wangpeng.firstfirebase.network.retroflow.adapters.FlowCallAdapter
import com.wangpeng.firstfirebase.utils.CommonInterceptor
import com.wangpeng.firstfirebase.utils.Constant
import com.wangpeng.firstfirebase.utils.SSLSocketClient
import com.wangpeng.firstfirebase.utils.loggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val LOG_INTERCEPTOR = "LOG_INTERCEPTOR"
const val COMMON_INTERCEPTOR = "COMMON_INTERCEPTOR"
const val OK_CLIENT = "OK_CLIENT"
const val SSL_CLIENT = "SSL_CLIENT"
const val HOST_VERIFIER = "HOST_VERIFIER"

val dataModule = module {
    factory(named(LOG_INTERCEPTOR)) { loggingInterceptor() }
    factory(named(SSL_CLIENT)) { SSLSocketClient.sSLSocketFactory }
    factory(named(HOST_VERIFIER)) { SSLSocketClient.hostnameVerifier }

    factory(named(COMMON_INTERCEPTOR)) {
        CommonInterceptor.create()
    }

    factory(named(OK_CLIENT)) {
        OkHttpClient.Builder().apply {
            connectTimeout(25, TimeUnit.SECONDS)
            readTimeout(18, TimeUnit.SECONDS)
            callTimeout(18, TimeUnit.SECONDS)
            addInterceptor(get(named(LOG_INTERCEPTOR)))
            addInterceptor(get(named(COMMON_INTERCEPTOR)))
            sslSocketFactory(get(named(SSL_CLIENT)))
            hostnameVerifier(get(named(HOST_VERIFIER)))
        }.build()
    }

    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RetroFlowCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory.create())
            .client(get(named(OK_CLIENT)))
            .baseUrl(Constant.NATIVE_API)
            .build()
            .create(DouBanApi::class.java)
    }

    single { DouBanRepository(get()) }
}
