package br.com.reconecta.api.model.organization

import br.com.reconecta.api.model.GetAddressDto
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.api.model.GetUserDto


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
    val user: GetUserDto = GetUserDto(),
    val availability: ArrayList<GetAvailabilityDto> = arrayListOf(),
    val residues: ArrayList<GetResidueDto> = arrayListOf(),
    val addresses: ArrayList<GetAddressDto> = arrayListOf(),
    val collects: ArrayList<GetCollectDto> = arrayListOf(),
    val logo: String? = null
)

