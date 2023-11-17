package br.com.reconecta.api.model

import com.google.gson.annotations.SerializedName


data class Organization(

    @SerializedName("isFavorite") var isFavorite: Boolean,
    @SerializedName("id") var id: Int,
    @SerializedName("cnpj") var cnpj: String,
    @SerializedName("name") var name: String,
    @SerializedName("phone") var phone: String,
    @SerializedName("description") var description: String,
    @SerializedName("type") var type: Int,
    @SerializedName("corporateReason") var corporateReason: String,
    @SerializedName("creationDate") var creationDate: String,
    @SerializedName("updateDate") var updateDate: String,
    @SerializedName("user") var user: String,
    @SerializedName("availability") var availability: String,
    @SerializedName("residues") var residues: String,
    @SerializedName("addresses") var addresses: ArrayList<String> = arrayListOf(),
    @SerializedName("points") var points: String,
    @SerializedName("collects") var collects: ArrayList<String> = arrayListOf()

)