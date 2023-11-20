package br.com.reconecta.api.model

data class GetCollectResidueDto(
    val id: Int? = null,
    val quantity: Int? = null,
    val creationDate: String? = null,
    val updateDate: String? = null,
    val points: Int? = null,
    val residueId: Int? = null,
    val collectId: Int? = null,
    val residue: GetResidueDto? = GetResidueDto(),
    val collect: String? = null
)