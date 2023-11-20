package br.com.reconecta.components.commons.formatters

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeFormatter {

    fun formatToExtendedDate(date: Date): String{
        val locale = Locale("pt", "BR")
        var formatter = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", locale)

        return formatter.format(date)
    }

    fun formatToShortDate(date: Date): String{
        val locale = Locale("pt", "BR")
        var formatter = SimpleDateFormat("dd/MM", locale)

        return formatter.format(date)
    }
}