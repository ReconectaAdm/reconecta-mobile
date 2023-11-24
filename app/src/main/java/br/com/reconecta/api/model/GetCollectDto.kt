package br.com.reconecta.api.model

import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.model.organization.GetOrganizationDto
import java.time.LocalDateTime

data class GetCollectDto(
    val id: Int = 0,
    val status: CollectStatus? = null,
    val value: Float? = null,
    val creationDate: LocalDateTime? = null,
    val updateDate: LocalDateTime? = null,
    val establishmentId: Int? = null,
    val organizationId: Int? = null,
    val date: LocalDateTime? = null,
    val hour: String? = null,
    val establishment: GetEstablishmentDto? = null,
    val organization: GetOrganizationDto? = null,
    val company: String? = null,
    val residues: ArrayList<GetCollectResidueDto> = arrayListOf(),
    var rating: GetCollectRatingDto? = null
)