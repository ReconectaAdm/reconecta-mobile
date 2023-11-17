package br.com.reconecta.api.model

import br.com.reconecta.api.model.enums.UnitMeasure
import com.google.gson.annotations.SerializedName


data class Residue(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("unitMeasure") val unitMeasure: UnitMeasure? = null,
    @SerializedName("typeId") val typeId: Int? = null,
    @SerializedName("creationDate") val creationDate: String? = null,
    @SerializedName("updateDate") val updateDate: String? = null,
    @SerializedName("organizationId") val organizationId: Int? = null,
    @SerializedName("organization") val organization: String? = null,
    @SerializedName("type") val type: String? = null
)