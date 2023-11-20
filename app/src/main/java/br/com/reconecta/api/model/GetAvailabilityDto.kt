package br.com.reconecta.api.model


data class GetAvailabilityDto(
    val id: Int = 0,
    val companyId: Int = 0,
    val day: Int = 0,
    val startHour: String = "",
    val endHour: String = "",
    val available: Boolean = true
)