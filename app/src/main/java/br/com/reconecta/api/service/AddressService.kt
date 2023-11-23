package br.com.reconecta.api.service

import br.com.reconecta.api.model.auth.Address
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressService {
    @POST("api/address/company")
    fun addAddress(@Body addresses: List<Address>): Call<Void>

    @DELETE("api/address/company/{id}")
    fun deleteById(@Path("id") id: Int): Call<Void>
}