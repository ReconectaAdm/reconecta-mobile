package br.com.reconecta.api.model


data class GetOrganizationDto(
    var isFavorite: Boolean? = null,
    val id: Int? = null,
    val cnpj: String = "",
    val name: String = "",
    val phone: String? = null,
    val description: String? = null,
    val type: Int = 0,
    val corporateReason: String = "",
    val creationDate: String? = null,
    val updateDate: String? = null,
    val availability: ArrayList<GetAvailabilityDto> = arrayListOf(),
    val residues: ArrayList<GetResidueDto> = arrayListOf(),
    val addresses: ArrayList<GetAddressDto> = arrayListOf(),
    val collects: ArrayList<GetCollectDto> = arrayListOf(),
    val logo: String? = null
)

