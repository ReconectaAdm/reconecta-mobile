package br.com.reconecta.api.model

import br.com.reconecta.api.model.enums.UnitMeasure
import java.time.LocalDateTime
import java.util.Date

data class GetResidueDto(
    val id: Int = 0,
    val name: String = "",
    val unitMeasure: UnitMeasure = UnitMeasure.UNITY,
    val typeId: Int = 0,
    val creationDate: LocalDateTime? = null,
    val updateDate: LocalDateTime? = null,
    val organizationId: Int = 0,
    val amountPaid: Float = 0f
)