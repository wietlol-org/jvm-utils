package me.wietlol.utils.json

import com.fasterxml.jackson.databind.ObjectMapper

class JacksonSerializerAdapter(
	val objectMapper: ObjectMapper
) : SimpleJsonSerializer
{
	override fun serialize(entity: Any?): String =
		objectMapper.writeValueAsString(entity)
	
	override fun <T> deserialize(json: String, type: Class<T>): T =
		objectMapper.readValue(json, type)
}
