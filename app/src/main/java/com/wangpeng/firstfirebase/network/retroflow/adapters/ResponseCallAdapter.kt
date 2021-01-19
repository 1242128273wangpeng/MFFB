package  com.wangpeng.firstfirebase.network.retroflow.adapters

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class ResponseCallAdapter<T : Any>(
    private val responseType: Type
) : CallAdapter<T, Flow<Response<T>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Flow<Response<T>> = flow {
        emit(
            suspendCancellableCoroutine<Response<T>> { coroutine ->
                call.enqueue(object : Callback<T> {

                    override fun onFailure(call: Call<T>, t: Throwable) =
                        coroutine.resumeWithException(t)

                    override fun onResponse(call: Call<T>, response: Response<T>) =
                        coroutine.resume(response)
                })
                coroutine.invokeOnCancellation { call.cancel() }
            }
        )
    }
}