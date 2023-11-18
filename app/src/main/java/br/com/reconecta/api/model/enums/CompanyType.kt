package br.com.reconecta.api.model.enums

import com.google.gson.annotations.SerializedName

enum class CompanyType(val value: Int) {
    @SerializedName("1")
    ORGANIZATION(1),
    @SerializedName("2")
    ESTABLISHMENT(2)
}