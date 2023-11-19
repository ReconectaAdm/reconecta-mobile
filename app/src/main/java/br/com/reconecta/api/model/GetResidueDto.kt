package br.com.reconecta.api.model

import br.com.reconecta.api.model.enums.UnitMeasure
import java.util.Date


data class GetResidueDto(
    val id: Int? = null,
    val name: String? = null,
    val unitMeasure: UnitMeasure? = null,
    val typeId: Int? = null,
    val creationDate: Date? = null,
    val updateDate: Date? = null,
    val organizationId: Int? = null,
    val organization: String? = null,
    val type: String? = null
)