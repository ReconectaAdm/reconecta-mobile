package br.com.reconecta.api.service

import android.content.Context
import br.com.reconecta.core.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private lateinit var organizationService: OrganizationService
    private lateinit var authService: AuthService

    companion object {
        private const val BASE_URL = "https://reconecta-app-dev.azurewebsites.net/"
    }

    fun getOrganizationService(context: Context): OrganizationService {
        if (!::organizationService.isInitialized) {
            organizationService = baseRetrofit(context).create(OrganizationService::class.java)
        }

        return organizationService
    }

    fun getAuthService(context: Context): AuthService {
        if (!::authService.isInitialized) {
            authService = baseRetrofit(context).create(AuthService::class.java)
        }

        return authService
    }

    private fun baseRetrofit(context: Context) =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(context)).build()


    private fun okhttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(AuthInterceptor(context)).build()

}