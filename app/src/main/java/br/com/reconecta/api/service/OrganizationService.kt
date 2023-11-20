package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateAccountRequest
import br.com.reconecta.api.model.GetOrganizationDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Streaming

interface OrganizationService {

    @GET("api/organization")
    fun getAll(): Call<List<GetOrganizationDto>>

    @GET("api/organization/{id}")
    fun getById(@Path("id") id: Int): Call<GetOrganizationDto>

    @POST("api/organization")
    fun create(@Body createOrganization: CreateAccountRequest): Call<CreateAccountRequest>
}