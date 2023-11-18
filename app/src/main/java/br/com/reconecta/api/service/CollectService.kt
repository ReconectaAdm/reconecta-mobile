package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateCollectRatingRequest
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.GetCollectRatingDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CollectService {
    @GET("api/collect")
    fun getAll(): Call<List<GetCollectDto>>

    @GET("api/collect/{id}")
    fun getById(@Path("id") id: Int): Call<GetCollectDto>

    @GET("api/collect/rating/{collectId}")
    fun getRatingByCollectId(@Path("collectId") collectId: Int): Call<GetCollectRatingDto>

    @POST("api/collect/rating")
    fun createRating(@Body createCollectRating: CreateCollectRatingRequest): Call<GetCollectRatingDto>
}