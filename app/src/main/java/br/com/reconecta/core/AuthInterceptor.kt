package br.com.reconecta.core

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiIxMCIsIkNvbXBhbnlJZCI6IjIxIiwiVHlwZSI6IjIiLCJuYmYiOjE3MDA3Nzk2MzUsImV4cCI6MTcwMDc4NjgzNSwiaWF0IjoxNzAwNzc5NjM1fQ._YvpXnHvJM-9VHmMrLzHKvVVRQKzjFbpDRcPQ0ceAlc")
        }

        return chain.proceed(requestBuilder.build())
    }
}