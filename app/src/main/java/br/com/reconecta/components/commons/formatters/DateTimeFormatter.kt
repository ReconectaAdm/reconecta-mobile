package br.com.reconecta.components.commons.formatters

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeFormatter {

    fun getFormattedExtendedDate(date: Date): String{
        val locale = Locale("pt", "BR")
        var formatter = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", locale)

        return formatter.format(date)
    }
}