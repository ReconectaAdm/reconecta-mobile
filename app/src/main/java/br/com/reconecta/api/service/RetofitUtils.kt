package br.com.reconecta.api.service

import android.util.Log
import androidx.compose.runtime.MutableState
import br.com.reconecta.api.model.GetSummaryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> handleRetrofitApiCall(
    call: Call<T>,
    isLoading: MutableState<Boolean>,
    onResponse: (Response<T>) -> Unit? = {},
    onFailure: (Call<T>, t: Throwable) -> Unit = { _: Call<T>, _: Throwable -> }
) {
    isLoading.value = true
    call.enqueue(object : Callback<T> {
        override fun onResponse(
            call: Call<T>,
            response: Response<T>
        ) {
            isLoading.value = false
            onResponse(response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            isLoading.value = false
            Log.i("API call error", t.message + t.stackTraceToString())
            onFailure(call, t)
        }
    })
}

fun <T> handleRetrofitApiCall(
    call: Call<T>,
    onResponse: (Response<T>) -> Unit = {},
    onFailure: (Call<T>, t: Throwable) -> Unit = { _: Call<T>, _: Throwable -> }
) {
    call.enqueue(object : Callback<T> {
        override fun onResponse(
            call: Call<T>,
            response: Response<T>
        ) {
            onResponse(response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.i("API call error", t.message + t.stackTraceToString())
            onFailure(call, t)
        }
    })
}

fun <T> handleRetrofitApiCall(
    call: Call<T>,
    func: (Response<T>)-> Unit? = {},
    setIsLoading: (Boolean) -> Unit,
    setState: (T) -> Unit
) {
    setIsLoading(true)
    call.enqueue(object : Callback<T> {
        override fun onResponse(
            call: Call<T>,
            response: Response<T>
        ) {
            setIsLoading(false)
            response.body()?.let { Log.i("response", it.toString()); setState(it) }
            func(response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.i("API call error", t.message + t.stackTraceToString())
            setIsLoading(false)
        }


    })
}
