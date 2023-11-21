package br.com.reconecta.core

import br.com.reconecta.api.service.adapter.LocalDateTimeTypeAdapter
import com.google.gson.GsonBuilder
import java.time.LocalDateTime

fun createGsonSerializer() = GsonBuilder().registerTypeAdapter(
    LocalDateTime::class.java, LocalDateTimeTypeAdapter()
).create()