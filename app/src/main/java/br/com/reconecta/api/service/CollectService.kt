package br.com.reconecta.api.service

import br.com.reconecta.api.model.GetSummaryResponse
import retrofit2.Call
import retrofit2.http.GET

interface CollectService {
    @GET("api/collect/summary")
    fun getSummary(): Call<GetSummaryResponse>
}