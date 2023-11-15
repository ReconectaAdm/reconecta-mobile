package br.com.reconecta.api.model;

import br.com.reconecta.api.model.enums.CollectStatus
import kotlinx.datetime.LocalDate

data class CreateCollectRequest(
        val date: LocalDate?,
        val hour: String,
        val status: CollectStatus,
        val organizationId: Int = 0,
        val value: Double = 0.0
)
