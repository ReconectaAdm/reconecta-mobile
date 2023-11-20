package br.com.reconecta.components.commons.formatters

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Date
import java.util.Locale

object CurrencyFormatter {

    fun format(value: Float): String{
        val formatter: NumberFormat = NumberFormat.getCurrencyInstance()
        formatter.maximumFractionDigits = 2
        formatter.currency = Currency.getInstance(Locale("pt", "BR"))

        return formatter.format(value)
    }
}