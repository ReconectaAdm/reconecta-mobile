package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateAccountRequest
import br.com.reconecta.api.model.auth.Company
import br.com.reconecta.api.model.organization.GetOrganizationDto
import br.com.reconecta.api.model.organization.UpdateOrganizationRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Part
import retrofit2.http.Path


interface IBaseCompanyService {
    fun getAll(): Call<List<GetOrganizationDto>>

    fun create(@Body createOrganization: CreateAccountRequest): Call<CreateAccountRequest>

    fun getMe(): Call<Company>

    fun updateById(
        @Path("id") id: Int,
        @Body updateOrganizationRequest: UpdateOrganizationRequest
    ): Call<Void>

    fun uploadLogo(
        @Part filePart: MultipartBody.Part,
        @Part contentType : String,

    ): Call<Void>
}