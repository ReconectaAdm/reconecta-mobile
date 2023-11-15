package br.com.reconecta.api.service

import br.com.reconecta.api.model.GetAvailabilityDto
import retrofit2.Call
import retrofit2.http.GET

interface AvailabilityService {
    @GET("api/availability")
    fun getAll(): Call<List<GetAvailabilityDto>>
}