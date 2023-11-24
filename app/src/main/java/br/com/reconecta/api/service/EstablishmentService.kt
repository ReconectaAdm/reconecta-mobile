package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateCompanyRequest
import br.com.reconecta.api.model.auth.Company
import br.com.reconecta.api.model.organization.GetOrganizationDto
import br.com.reconecta.api.model.organization.UpdateCompanyRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface EstablishmentService : IBaseCompanyService {

    companion object{
        private const val prefix = "api/establishment"
    }

    @GET(prefix)
    override fun getAll(): Call<List<GetOrganizationDto>>

    @POST(prefix)
    override fun create(@Body createOrganization: CreateCompanyRequest): Call<CreateCompanyRequest>

    @GET("$prefix/me")
    override fun getMe(): Call<Company>

    @PUT("$prefix/{id}")
    override fun updateById(
        @Path("id") id: Int,
        @Body updateOrganizationRequest: UpdateCompanyRequest
    ): Call<Void>

    @PATCH("$prefix/logo")
    @Multipart
    override fun uploadLogo(file: MultipartBody.Part): Call<Void>
}


