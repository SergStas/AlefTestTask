package com.example.aleftesttask.network.converters

import com.example.aleftesttask.network.entity.PicsListResponse
import com.google.gson.*
import java.lang.reflect.Type

object PicsListResponseDeserializer: JsonDeserializer<PicsListResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PicsListResponse {
        val jsonArray = json as JsonArray
        val resultList = jsonArray.map { it.asString }

        return PicsListResponse(resultList)
    }
}