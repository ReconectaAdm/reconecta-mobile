package br.com.reconecta.api.service

import br.com.reconecta.api.model.auth.LoginRequest
import br.com.reconecta.api.model.auth.LoginResponse
import br.com.reconecta.api.model.auth.UpdatePasswordRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {
    @POST("api/user/auth")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @PATCH("api/user/password")
    fun updatePassword(@Body updatePasswordRequest: UpdatePasswordRequest): Call<Void>

    @DELETE("api/user")
    fun deleteUser(): Call<Void>
}