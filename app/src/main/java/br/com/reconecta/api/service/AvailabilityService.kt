package br.com.reconecta.api.service

import br.com.reconecta.api.model.GetAvailabilityDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AvailabilityService {
    @GET("api/availability")
    fun getAll(): Call<List<GetAvailabilityDto>>

    @GET("api/availability/organization/{organizationId}")
    fun getByOrganizationId(@Path("organizationId") organizationId: Int): Call<List<GetAvailabilityDto>>
}