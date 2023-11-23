package br.com.reconecta.api.model.organization

import br.com.reconecta.api.model.auth.Address

data class GetOgranizationMeResponse(
    val isFavorite: Boolean,
    val id: Int,
    val cnpj: String,
    val name: String,
    val description: String,
    val type: Int,
    val corporateReason: String,
    val phone: String,
    val address: Address
)
