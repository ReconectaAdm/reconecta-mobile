package br.com.reconecta.api.model


data class GetCollectRatingDto(
    val comment: String? = null,
    val punctuality: Float? = null,
    val satisfaction: Float? = null,
    val collectId: Int? = null
)