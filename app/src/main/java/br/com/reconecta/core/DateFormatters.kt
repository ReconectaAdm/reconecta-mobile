package br.com.reconecta.core

import java.time.format.DateTimeFormatter

object DateFormatters {
    val DEFAULT_LOCAL_DATE_TIME: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val BRAZIL_LOCAL_DATE_TIME: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
    val BRAZIL_LOCAL_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy")
}