package br.com.reconecta.api.model;

import br.com.reconecta.api.model.enums.CollectStatus
import kotlinx.datetime.LocalDate
import java.util.Date

data class CreateCollectRequest(
    val date: Date,
    val hour: String,
    val status: Int,
    val organizationId: Int = 0,
    val value: Double = 0.0,
    val residues: List<CreateCollectResidueRequest>
)

data class CreateCollectResidueRequest(
    val residueId: Int,
    var quantity: Int
)