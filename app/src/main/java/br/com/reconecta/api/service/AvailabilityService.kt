package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateAvailabilityRequest
import br.com.reconecta.api.model.GetAvailabilityDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AvailabilityService {
    @GET("api/availability")
    fun getAll(): Call<List<GetAvailabilityDto>>

    @GET("api/availability/organization/{organizationId}")
    fun getByOrganizationId(@Path("organizationId") organizationId: Int): Call<List<GetAvailabilityDto>>

    @PUT("api/availability")
    fun update(@Body availabilityRequest: List<CreateAvailabilityRequest>): Call<ResponseBody>
}