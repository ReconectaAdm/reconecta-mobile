package br.com.reconecta.api.model;

data class CreateCollectRatingRequest(
        val comment: String?,
        val punctuality: Float,
        val satisfaction: Float,
        val collectId: Int = 0
)
