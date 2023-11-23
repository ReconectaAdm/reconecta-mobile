package br.com.reconecta.api.model.organization

import br.com.reconecta.api.model.auth.Address

data class UpdateCompanyRequest(
    val cnpj: String,
    val name: String,
    val corporateReason: String,
    val description: String,
    val phone: String,
    val addresses: List<Address>
)
