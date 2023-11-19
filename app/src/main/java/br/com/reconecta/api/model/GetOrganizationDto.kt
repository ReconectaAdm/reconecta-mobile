package br.com.reconecta.api.model


data class GetOrganizationDto(
    var isFavorite: Boolean,
    val id: Int,
    val cnpj: String,
    val name: String,
    val phone: String,
    val description: String,
    val type: Int,
    val corporateReason: String,
    val creationDate: String,
    val updateDate: String,
    val user: String,
    val availability: String,
    val residues: String,
    val addresses: ArrayList<String> = arrayListOf(),
    val points: String,
    val collects: ArrayList<String> = arrayListOf(),
    val logo: String,
    val rating: Double,
)

