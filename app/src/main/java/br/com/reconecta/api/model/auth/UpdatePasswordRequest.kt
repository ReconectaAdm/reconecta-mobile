package br.com.reconecta.api.model.auth


data class UpdatePasswordRequest(val password: String, val email: String)