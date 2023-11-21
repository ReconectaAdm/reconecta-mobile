package br.com.reconecta.api.service

import android.util.Log
import androidx.compose.runtime.MutableState
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> handleApiResponse(
    call: Call<T>,
    state: MutableState<T>? = null,
    isLoading: MutableState<Boolean>,
    func: (Response<T>)-> Unit? = {}
) {
    isLoading.value = true
    call.enqueue(object : Callback<T> {
        override fun onResponse(
            call: Call<T>,
            response: Response<T>
        ) {
            isLoading.value = false
            response.body()?.let { state?.value = it }
            func(response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.i("API call error", t.message + t.stackTraceToString())
            isLoading.value = false
        }
    })
}

fun <T> handleApiResponse(
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

