package br.com.reconecta.api.model.auth

import br.com.reconecta.api.model.residue.GetResidueResponse
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
    val user: UserResponse?,
    val availability: Any?,
    val residues: List<GetResidueResponse>,
    val addresses: List<Address>,
    val collects: List<Any>
)

data class Address(
    val id: Int? = null,
    val street: String,
    val number: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val latitude: Float? = null,
    val longitude: Float? = null,
)
