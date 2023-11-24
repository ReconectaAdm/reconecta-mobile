package br.com.reconecta.api.service.adapter

import br.com.reconecta.core.DateFormatters
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDateTime

class LocalDateTimeTypeAdapter : JsonSerializer<LocalDateTime?>,
    JsonDeserializer<LocalDateTime> {
    override fun serialize(
        localDateTime: LocalDateTime?,
        srcType: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(DateFormatters.DEFAULT_LOCAL_DATE_TIME.format(localDateTime))
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDateTime {
        return  LocalDateTime.parse(json.asString.replace("\"".toRegex(), ""), DateFormatters.DEFAULT_LOCAL_DATE_TIME)
    }
}