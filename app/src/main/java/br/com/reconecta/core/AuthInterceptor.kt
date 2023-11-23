package br.com.reconecta.core

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiIyMiIsIkNvbXBhbnlJZCI6IjU3IiwiVHlwZSI6IjIiLCJuYmYiOjE3MDA3ODA2NTUsImV4cCI6MTcwMDc4Nzg1NSwiaWF0IjoxNzAwNzgwNjU1fQ.UIPTAhaGmBpBUFvRIvljfKHO8jSF474FkZbgUL1keA0")
        }

        return chain.proceed(requestBuilder.build())
    }
}