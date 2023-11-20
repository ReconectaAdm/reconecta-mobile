package br.com.reconecta.components.commons.formatters

object PhoneNumberFormatter {
    fun format(phone: String): String {
        val numbers = phone.replace(Regex("\\D"), "")

        return when (numbers.length) {
            11 -> {
                // Model (XX) XXXXX-XXXX
                "(${numbers.substring(0, 2)}) ${numbers.substring(2, 7)}-${numbers.substring(7)}"
            }
            10 -> {
                // Modelo (XX) XXXX-XXXX
                "(${numbers.substring(0, 2)}) ${numbers.substring(2, 6)}-${numbers.substring(6)}"
            }
            else -> {
                // Caso não seja possível determinar o modelo
                "Invalid phone format"
            }
        }
    }
}

