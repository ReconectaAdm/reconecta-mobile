package br.com.reconecta.api.model.residue

import br.com.reconecta.enums.EUnitMeasure
import java.time.LocalDateTime

data class GetResidueResponse(
    val id: Int,
    val name: String,
    val unitMeasure: EUnitMeasure,
    val amountPaid: Double,
    val typeId: Int,
    val creationDate: LocalDateTime,
    val updateDate: LocalDateTime,
    val organizationId: Int
)
