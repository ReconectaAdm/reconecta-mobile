package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateCollectRatingRequest
import br.com.reconecta.api.model.CreateCollectRequest
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.GetCollectRatingDto
import br.com.reconecta.api.model.GetSummaryResponse
import br.com.reconecta.api.model.enums.CollectStatus
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

interface CollectService {
    @GET("api/collect")
    fun getAll(): Call<List<GetCollectDto>>

    @GET("api/collect/{id}")
    fun getById(@Path("id") id: Int): Call<GetCollectDto>

    @POST("api/collect")
    fun createCollect(@Body request: CreateCollectRequest): Call<GetCollectDto>

    @PATCH("api/collect/{id}/status/{status}")
    fun updateStatus(@Path("id") id: Int, @Path("status") status: CollectStatus): Call<ResponseBody>

    @GET("api/collect/summary")
    fun getSummary(): Call<GetSummaryResponse>

    @GET("api/collect/summary")
    fun getSummaryAsync(@Query("initialDate") initialDate: LocalDate? = null, @Query("endDate") endDate: LocalDate? = null): Call<GetSummaryResponse>

    @GET("api/collect/rating/{collectId}")
    fun getRatingByCollectId(@Path("collectId") collectId: Int): Call<GetCollectRatingDto>

    @GET("api/collect/rating/organization/{organizationId}")
    fun getRatingByOrganizationId(@Path("organizationId") organizationId: Int): Call<List<GetCollectRatingDto>>

    @POST("api/collect/rating")
    fun createRating(@Body createCollectRating: CreateCollectRatingRequest): Call<GetCollectRatingDto>
}