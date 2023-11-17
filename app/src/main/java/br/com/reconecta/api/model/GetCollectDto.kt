package br.com.reconecta.api.model

import br.com.reconecta.api.model.enums.CollectStatus
import com.google.gson.annotations.SerializedName

data class GetCollectDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("status") val status: CollectStatus? = null,
    @SerializedName("value") val value: Double? = null,
    @SerializedName("creationDate") val creationDate: String? = null,
    @SerializedName("updateDate") val updateDate: String? = null,
    @SerializedName("establishmentId") val establishmentId: Int? = null,
    @SerializedName("organizationId") val organizationId: Int? = null,
    @SerializedName("date") val date: String? = null,
    @SerializedName("hour") val hour: String? = null,
    @SerializedName("establishment") val establishment: Establishment? = null,
    @SerializedName("organization") val organization: Organization? = null,
    @SerializedName("company") val company: String? = null,
    @SerializedName("residues") val residues: ArrayList<Residues> = arrayListOf(),
    @SerializedName("rating") val rating: String? = null
)