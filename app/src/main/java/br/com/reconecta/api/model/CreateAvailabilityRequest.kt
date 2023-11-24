package br.com.reconecta.api.model


data class CreateAvailabilityRequest(
    val id: Int = 0,
    val day: Int = 0,
    var startHour: String = "",
    var endHour: String = "",
    var available: Boolean
)

//fun GetAvailabilityDto.toAvailabilityRequest() = CreateAvailabilityRequest(
//    id = id,
//    day = day,
//    startHour = startHour,
//    endHour = endHour,
//    available = available
//)
