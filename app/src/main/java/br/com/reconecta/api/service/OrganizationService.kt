package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateAccountRequest
import br.com.reconecta.api.model.GetOrganizationDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrganizationService {
    @GET("api/organization")
    fun getAll(): Call<List<GetOrganizationDto>>

    @POST("api/organization")
    fun create(@Body createOrganization: CreateAccountRequest): Call<CreateAccountRequest>
}