package br.com.reconecta.api.service

import br.com.reconecta.api.model.GetCollectDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectService {
    @GET("api/collect")
    fun getAll(): Call<List<GetCollectDto>>
    @GET("api/collect/{id}")
    fun getById(@Path("id") id: Int): Call<GetCollectDto>
}