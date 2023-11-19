package br.com.reconecta.api.model.enums

import com.google.gson.annotations.SerializedName

enum class UnitMeasure(val value: Int) {
    @SerializedName("1")
    UNITY(1),
    @SerializedName("2")
    LITER(2),
    @SerializedName("3")
    KILO(3);
}

fun mapUnitMeasure(unitMeasure: UnitMeasure): String {
    return when (unitMeasure) {
        UnitMeasure.KILO -> "Quilo(s)"
        UnitMeasure.LITER -> "Litro(s)"
        UnitMeasure.UNITY -> "Unidade(s)"
    }
}

