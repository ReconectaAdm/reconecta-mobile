package br.com.reconecta.api.service

import br.com.reconecta.api.model.LoginRequest
import br.com.reconecta.api.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/auth")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}