package br.com.reconecta.components.commons.formatters

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object DateTimeFormatter {

    fun formatToExtendedDate(date: LocalDateTime): String{
        val locale = Locale("pt", "BR")
        val formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", locale)

        return formatter.format(date)
    }

    fun formatToShortDate(date: LocalDateTime): String{
        val locale = Locale("pt", "BR")
        val formatter = DateTimeFormatter.ofPattern("dd/MM", locale)

        return formatter.format(date)
    }

}