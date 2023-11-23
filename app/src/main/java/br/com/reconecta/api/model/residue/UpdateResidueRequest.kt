package br.com.reconecta.api.model.residue

data class UpdateResidueRequest(
    val name: String,
    val unitMeasure: Int,
    val type: Int,
    val amountPaid: Double
)
