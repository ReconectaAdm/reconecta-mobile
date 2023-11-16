package br.com.reconecta.api.model


data class UpdatePasswordRequest(val password: String, val email: String)