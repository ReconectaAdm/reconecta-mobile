package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateCompanyRequest
import br.com.reconecta.api.model.auth.Company
import br.com.reconecta.api.model.organization.GetOrganizationDto
import br.com.reconecta.api.model.organization.UpdateCompanyRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Part
import retrofit2.http.Path


interface IBaseCompanyService {
    fun getAll(): Call<List<GetOrganizationDto>>

    fun create(@Body createOrganization: CreateCompanyRequest): Call<CreateCompanyRequest>

    fun getMe(): Call<Company>

    fun updateById(
        @Path("id") id: Int,
        @Body updateOrganizationRequest: UpdateCompanyRequest
    ): Call<Void>

    fun uploadLogo(
        @Part file: MultipartBody.Part,
    ): Call<Void>
}