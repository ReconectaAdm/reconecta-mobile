package br.com.reconecta.api.model

data class GetSummaryResponse(
    val collects: Int = 0,
    val points: Int = 0,
    val value: Double = 0.0,
    val residues: List<TypeResidueItem> = emptyList(),
    val status: List<CollectStatusItem> = emptyList()
)
data class CollectStatusItem(
    val id: Int,
    val name: String,
    val qtd: Int
)