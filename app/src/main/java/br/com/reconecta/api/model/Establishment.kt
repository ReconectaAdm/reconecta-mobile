package br.com.reconecta.api.model
import com.google.gson.annotations.SerializedName


data class Establishment(
    @SerializedName("id") val id: Int,
    @SerializedName("cnpj") val cnpj: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("type") val type: Int,
    @SerializedName("corporateReason") val corporateReason: String,
    @SerializedName("creationDate") val creationDate: String,
    @SerializedName("updateDate") val updateDate: String,
    @SerializedName("user") val user: String,
    @SerializedName("availability") val availability: String,
    @SerializedName("residues") val residues: String,
    @SerializedName("addresses") val addresses: ArrayList<String> = arrayListOf(),
    @SerializedName("points") val points: String,
    @SerializedName("collects") val collects: ArrayList<String> = arrayListOf()

)