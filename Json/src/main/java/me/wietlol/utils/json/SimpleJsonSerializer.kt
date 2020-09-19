package me.wietlol.utils.json

interface SimpleJsonSerializer
{
	fun serialize(entity: Any?): String
	
	fun <T> deserialize(json: String, type: Class<T>): T
}

inline fun <reified T> SimpleJsonSerializer.deserialize(json: String): T =
	deserialize(json, T::class.java)
