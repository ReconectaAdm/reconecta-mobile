package br.com.reconecta.api.model

import java.util.Date
data class GetAddressDto(
    val id: Int = 0,
    val street: String = "",
    val number: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val latitude: Float? = null,
    val longitude: Float? = null,
    val creationDate: Date,
    val updatedDate: Date? = null,
    val companyId: Int = 0,
    val distance: Float = 0f
)

