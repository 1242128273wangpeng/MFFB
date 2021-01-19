package com.wangpeng.firstfirebase.network.converters

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class NullOnEmptyConverterFactory : Converter.Factory() {

    companion object {
        fun create(): Converter.Factory =
            NullOnEmptyConverterFactory()
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter<ResponseBody, Any> { body ->
            val contentLength = body.contentLength()
            if (contentLength == 0L) {
                null
            } else delegate.convert(body)
        }
    }
}