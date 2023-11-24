package br.com.reconecta.core

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiIxOCIsIkNvbXBhbnlJZCI6IjQ2IiwiVHlwZSI6IjEiLCJuYmYiOjE3MDA3ODQ4NjcsImV4cCI6MTcwMDc5MjA2NywiaWF0IjoxNzAwNzg0ODY3fQ.-kGLRektTcWaxnVXrQ2aawTimQ3jYQA96QawPyDL_6Y")
        }

        return chain.proceed(requestBuilder.build())
    }
}