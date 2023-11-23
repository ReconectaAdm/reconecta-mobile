package br.com.reconecta.api.service

import android.content.Context
import br.com.reconecta.core.AuthInterceptor
import br.com.reconecta.core.createGsonSerializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private lateinit var organizationService: OrganizationService
    private lateinit var establishmentService: EstablishmentService
    private lateinit var collectService: CollectService
    private lateinit var userService: UserService
    private lateinit var residueService: ResidueService
    private lateinit var addressService: AddressService

    companion object {
        private const val BASE_URL = "https://reconecta-app-dev.azurewebsites.net/"
    }

    fun getAddressService(context: Context): AddressService {
        if (!::addressService.isInitialized) {
            addressService = baseRetrofit(context).create(AddressService::class.java)
        }

        return addressService
    }

    fun getOrganizationService(context: Context): OrganizationService {
        if (!::organizationService.isInitialized) {
            organizationService = baseRetrofit(context).create(OrganizationService::class.java)
        }

        return organizationService
    }

    fun getResidueService(context: Context): ResidueService {
        if (!::residueService.isInitialized) {
            residueService = baseRetrofit(context).create(ResidueService::class.java)
        }

        return residueService
    }


    fun getCollectService(context: Context): CollectService {
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

    fun getAuthService(context: Context): UserService {
        if (!::userService.isInitialized) {
            userService = baseRetrofit(context).create(UserService::class.java)
        }

        return userService
    }

    private fun baseRetrofit(context: Context, okHttpClient: OkHttpClient? = null) =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create(createGsonSerializer())
        ).client(okHttpClient ?: okhttpClient(context)).build()


    private fun okhttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(AuthInterceptor(context)).build()

}