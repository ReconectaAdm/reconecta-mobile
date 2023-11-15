package br.com.reconecta.api.model

data class CreateAccountRequest(
    val user: UserModel,
    val cnpj: String,
    val name: String,
    val description: String,
    val corporateReason: String,
    val addresses: List<AddressModel>
)

data class UserModel (
    val email: String,
    val password:String
)
data class AddressModel(
    val street: String,
    val number: String,
    val city: String,
    val state: String,
    val postalCode: String
)