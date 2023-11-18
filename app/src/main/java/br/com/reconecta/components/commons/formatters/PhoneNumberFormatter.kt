package br.com.reconecta.components.commons.formatters

fun phoneNumberFormatter(phone: String): String {
    // Remove caracteres não numéricos
    val numbers = phone.replace(Regex("[^\\d]"), "")

    // Verifica o comprimento da string para determinar o modelo
    return if (numbers.length == 11) {
        // Modelo (XX) XXXXX-XXXX
        "(${numbers.substring(0, 2)}) ${numbers.substring(2, 7)}-${numbers.substring(7)}"
    } else if (numbers.length == 10) {
        // Modelo (XX) XXXX-XXXX
        "(${numbers.substring(0, 2)}) ${numbers.substring(2, 6)}-${numbers.substring(6)}"
    } else {
        // Caso não seja possível determinar o modelo
        "Invalid phone format"
    }
}
