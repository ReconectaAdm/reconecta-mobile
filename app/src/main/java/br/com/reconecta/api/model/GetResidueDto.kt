package br.com.reconecta.api.model

import br.com.reconecta.api.model.enums.UnitMeasure
import java.util.Date

data class GetResidueDto(
    val id: Int = 0,
    val name: String = "",
    val unitMeasure: UnitMeasure = UnitMeasure.UNITY,
    val typeId: Int = 0,
    val creationDate: Date? = null,
    val updateDate: Date? = null,
    val organizationId: Int = 0,
    val amountPaid: Float = 0f
)