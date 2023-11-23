package br.com.reconecta.api.service

import android.content.Context
import androidx.navigation.NavHostController
import br.com.reconecta.core.AuthInterceptor
import br.com.reconecta.core.createGsonSerializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private lateinit var organizationService: OrganizationService
    private lateinit var establishmentService: EstablishmentService
    private lateinit var collectService: CollectService
    private lateinit var authService: AuthService
    private lateinit var availabilityService: AvailabilityService
    private lateinit var residueService: ResidueService

    companion object {
        private const val BASE_URL = "https://reconecta-app-dev.azurewebsites.net/"
    }

    fun getOrganizationService(context: Context): OrganizationService {
        if (!::organizationService.isInitialized) {
            organizationService = baseRetrofit(context).create(OrganizationService::class.java)
        }

        return organizationService
    }

    fun getCollectService(context: NavHostController): CollectService {
        if (!::collectService.isInitialized) {
            collectService = baseRetrofit(context).create(CollectService::class.java)
        }

        return collectService
    }

    fun getEstablishmentService(context: Context): EstablishmentService {
        if (!::establishmentService.isInitialized) {
            establishmentService = baseRetrofit(context).create(EstablishmentService::class.java)
        }

        return establishmentService
    }

    fun getAuthService(context: Context): AuthService {
        if (!::authService.isInitialized) {
            authService = baseRetrofit(context).create(AuthService::class.java)
        }

        return authService
    }

    fun getAvailabilityService(context: Context): AvailabilityService {
        if (!::availabilityService.isInitialized) {
            availabilityService = baseRetrofit(context).create(AvailabilityService::class.java)
        }

        return availabilityService
    }

    fun getResidueService(context: Context): ResidueService {
        if (!::residueService.isInitialized) {
            residueService = baseRetrofit(context).create(ResidueService::class.java)
        }

        return residueService
    }

//    private fun baseRetrofit(context: Context) =
//        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
//            GsonConverterFactory.create(
//                GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
//            )
//        )
//            .client(okhttpClient(context)).build()
    private fun baseRetrofit(context: Context, okHttpClient: OkHttpClient? = null) =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create(createGsonSerializer())
        )
            .client(okHttpClient ?: okhttpClient(context)).build()


    private fun okhttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(AuthInterceptor(context)).build()

}