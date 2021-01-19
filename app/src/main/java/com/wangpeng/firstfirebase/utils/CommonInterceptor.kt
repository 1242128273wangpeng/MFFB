package com.wangpeng.firstfirebase.utils

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class CommonInterceptor : Interceptor {
    companion object {
        const val DOMAIN = "domain"
        private const val NATIVE_API_KEY = "NATIVE_API"
        private const val QUERY_API_KEY = "QUERY_API"
        val DOMAIN_NAME_HUB =
            hashMapOf(
                NATIVE_API_KEY to Constant.NATIVE_API,
                QUERY_API_KEY to Constant.QUERY_API
            )

        fun create(): Interceptor {
            return CommonInterceptor()
        }
    }

    @Synchronized
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val domainName = obtainDomainNameFromHeaders(request) ?: ""
        val reBuildRequest = rebuildRequest(request, domainName)
        return chain.proceed(reBuildRequest)
    }

    @Throws(IOException::class)
    private fun rebuildRequest(request: Request, domainName: String): Request {
        return when {
            "GET" == request.method() -> rebuildGetRequest(request, domainName)
            else -> request
        }
    }

    /**
     * 对get请求做统一参数处理
     */
    private fun rebuildGetRequest(request: Request, domainName: String): Request {
        val oldUrl = request.url()
        val requestBulider = request.newBuilder()
        if (domainName.isEmpty()) return request
        val resUrl = HttpUrl.parse(
            oldUrl.toString().replace(
                DOMAIN_NAME_HUB[NATIVE_API_KEY] ?: "", domainName
            )
        )
        return requestBulider.url(resUrl).build()
    }

    private fun obtainDomainNameFromHeaders(request: Request): String? {
        val headers = request.headers(DOMAIN)
        if (headers.size == 0) return null
        require(headers.size <= 1) { "Only one Domain in the headers" }
        return request.header(DOMAIN)
    }
}
