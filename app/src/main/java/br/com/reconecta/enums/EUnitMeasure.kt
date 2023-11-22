package br.com.reconecta.enums

import com.google.gson.annotations.SerializedName

enum class EUnitMeasure(val value: Int) {
    @SerializedName("1")
    UNITY(1),

    @SerializedName("2")
    LITER(2),

    @SerializedName("3")
    KILO(3);
}


fun mapUnitMeasure(unitMeasure: EUnitMeasure): String {
    return when (unitMeasure) {
        EUnitMeasure.KILO -> "Quilo(s)"
        EUnitMeasure.LITER -> "Litro(s)"
        EUnitMeasure.UNITY -> "Unidade(s)"
    }
}

fun mapAbrevUnitMeasure(unitMeasure: EUnitMeasure): String {
    return when (unitMeasure) {
        EUnitMeasure.KILO -> "kg"
        EUnitMeasure.LITER -> "lt"
        EUnitMeasure.UNITY -> "und"
    }
}

fun abreviatureToEnumNumber(unitMeasure: String): Int = when (unitMeasure) {
    "kg" -> EUnitMeasure.KILO.value
    "lt" -> EUnitMeasure.LITER.value
    "und" -> EUnitMeasure.UNITY.value
    else -> {
        throw Error("Invalid unit measure")
    }
}

