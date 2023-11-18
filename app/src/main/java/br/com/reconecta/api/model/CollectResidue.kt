package br.com.reconecta.api.model

import com.google.gson.annotations.SerializedName
data class CollectResidue(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("quantity") val quantity: Int? = null,
    @SerializedName("creationDate") val creationDate: String? = null,
    @SerializedName("updateDate") val updateDate: String? = null,
    @SerializedName("points") val points: Int? = null,
    @SerializedName("residueId") val residueId: Int? = null,
    @SerializedName("collectId") val collectId: Int? = null,
    @SerializedName("residue") val residue: Residue? = Residue(),
    @SerializedName("collect") val collect: String? = null
)