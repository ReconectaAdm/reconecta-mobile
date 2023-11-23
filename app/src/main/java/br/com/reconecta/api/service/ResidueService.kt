package br.com.reconecta.api.service

import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.api.model.residue.CreateResidueRequest
import br.com.reconecta.api.model.residue.GetResidueResponse
import br.com.reconecta.api.model.residue.GetResidueTypeResponse
import br.com.reconecta.api.model.residue.UpdateResidueRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ResidueService {

    @GET("api/residue")
    fun getAll(): Call<List<GetResidueDto>>

    @GET("api/residue/organization/{organizationId}")
    fun getResiduesByOrganizationId(@Path("organizationId") organizationId: Int): Call<List<GetResidueDto>>

    @GET("api/residue/type")
    fun getResidueTypes(): Call<List<GetResidueTypeResponse>>

    @GET("api/residue/organization/{id}")
    fun getResiduesByOrganization(@Path("id") organizationId: Int): Call<List<GetResidueResponse>>

    @POST("api/residue")
    fun createResidue(@Body createResidueRequest: CreateResidueRequest): Call<Void>

    @PUT("api/residue/{id}")
    fun updateResidueById(
        @Path("id") residueId: Int, @Body updateResidueRequest: UpdateResidueRequest
    ): Call<Void>

    @DELETE("api/residue/{id}")
    fun deleteResidueById(@Path("id") residueId: Int): Call<Void>
}