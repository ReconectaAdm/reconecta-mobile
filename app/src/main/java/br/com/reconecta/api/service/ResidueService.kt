package br.com.reconecta.api.service

import br.com.reconecta.api.model.CreateAccountRequest
import br.com.reconecta.api.model.GetOrganizationDto
import br.com.reconecta.api.model.GetResidueDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Streaming

interface ResidueService {
    @GET("api/residue")
    fun getAll(): Call<List<GetResidueDto>>

}