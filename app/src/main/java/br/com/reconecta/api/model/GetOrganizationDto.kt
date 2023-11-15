package br.com.reconecta.api.model


data class GetOrganizationDto(
    val logo: String,
    val description: String,
    val name: String,
    val rating: Double,
    var isFavorite: Boolean
)

