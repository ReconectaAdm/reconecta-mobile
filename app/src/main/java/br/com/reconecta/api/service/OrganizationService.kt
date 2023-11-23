package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateAccountRequest
import br.com.reconecta.api.model.auth.Company
import br.com.reconecta.api.model.organization.GetOrganizationDto
import br.com.reconecta.api.model.organization.UpdateOrganizationRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface OrganizationService : IBaseCompanyService {

    companion object {
        private const val prefix = "api/organization"
    }

    @GET(prefix)
    override fun getAll(): Call<List<GetOrganizationDto>>

    @GET("$prefix/me")
    override fun getMe(): Call<Company>

    @POST(prefix)
    override fun create(@Body createOrganization: CreateAccountRequest): Call<CreateAccountRequest>

    @PUT("$prefix/{id}")
    override fun updateById(
        @Path("id") id: Int, @Body updateOrganizationRequest: UpdateOrganizationRequest
    ): Call<Void>

    @PATCH("${prefix}/logo")
    @Multipart
    override fun uploadLogo(filePart: Part): Call<Void>
}