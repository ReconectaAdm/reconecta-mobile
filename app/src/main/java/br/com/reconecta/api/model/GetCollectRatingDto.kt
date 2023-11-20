package br.com.reconecta.api.model


data class GetCollectRatingDto(
    val comment: String? = null,
    val punctuality: Float = 0f,
    val satisfaction: Float = 0f,
    val collectId: Int? = null
)