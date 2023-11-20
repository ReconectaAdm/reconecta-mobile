package br.com.reconecta.api.model

import br.com.reconecta.api.model.enums.CollectStatus
import com.google.type.DateTime
import java.time.LocalDateTime
import java.util.Date

data class GetCollectDto(
    val id: Int = 0,
    val status: CollectStatus? = null,
    val value: Float? = null,
    val creationDate: Date? = null,
    val updateDate: Date? = null,
    val establishmentId: Int? = null,
    val organizationId: Int? = null,
    val date: Date? = null,
    val hour: String? = null,
    val establishment: GetEstablishmentDto? = null,
    val organization: GetOrganizationDto? = null,
    val company: String? = null,
    val residues: ArrayList<GetCollectResidueDto> = arrayListOf(),
    val rating: GetCollectRatingDto? = null
)