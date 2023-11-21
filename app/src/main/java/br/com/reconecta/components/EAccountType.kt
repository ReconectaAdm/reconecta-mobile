package br.com.reconecta.components

import com.google.gson.annotations.SerializedName

enum class EAccountType(val rawValue: Int) {
    @SerializedName("1")
    ORGANIZATION(1),

    @SerializedName("2")
    ESTABLISHMENT(2),

    UNSUPPORTED(-1);

    companion object {
        fun from(findValue: Int): EAccountType =
            values().firstOrNull { it.rawValue == findValue } ?: UNSUPPORTED
    }
}