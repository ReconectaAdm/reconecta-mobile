package br.com.reconecta.api.model

data class GetAvailabilityDto(
        val id: Int,
        val companyId: Int,
        val day: Int,
        val startHour: String,
        val endHour: String,
        val available: Boolean
)