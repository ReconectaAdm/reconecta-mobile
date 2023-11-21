package br.com.reconecta.api.model

data class GetSummaryResponse(
    val collects: Int,
    val points: Int,
    val value: Int,
    val residues: Any,
    val status: Any
)