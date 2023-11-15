package br.com.reconecta.api.service

import androidx.compose.runtime.MutableState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> handleApiResponse(call: Call<T>, state: MutableState<T>) {
    call.enqueue(object : Callback<T> {
        override fun onResponse(
            call: Call<T>,
            response: Response<T>
        ) {
            state.value = response.body()!!
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}