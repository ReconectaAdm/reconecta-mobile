package br.com.reconecta.api.model


data class GetEstablishmentDto(
    val id: Int,
    val cnpj: String,
    val name: String,
    val description: String,
    val type: Int,
    val corporateReason: String,
    val creationDate: String,
    val updateDate: String,
    val user: String,
    val availability: String,
    val residues: String,
    val phone: String?,
    val addresses: ArrayList<String> = arrayListOf(),
    val points: String,
    val collects: ArrayList<String> = arrayListOf()

)