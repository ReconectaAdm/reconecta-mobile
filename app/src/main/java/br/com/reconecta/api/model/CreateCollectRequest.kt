package br.com.reconecta.api.model;

import java.time.LocalDateTime


data class CreateCollectRequest(
    val date: LocalDateTime,
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