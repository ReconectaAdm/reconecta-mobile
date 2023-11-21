package br.com.reconecta.api.model

import br.com.reconecta.components.EAccountType
import java.time.LocalDateTime


data class LoginResponse(val token: String, val user: UserResponse)


data class UserResponse(
    val id: Int,
    val email: String,
    val companyId: Int,
    val company: Company
)

data class Company(
    val id: Int,
    val cnpj: String,
    val name: String,
    val description: String,
    val type: EAccountType,
    val corporateReason: String,
    var creationDate: LocalDateTime?,
    val user: UserResponse,
    val availability: Any?,
    val residues: Any?,
    val addresses: List<Address>,
    val collects: List<Any>
)

data class Address(
    val id: Int,
    val street: String? = null,
    val number: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
)
