package me.wietlol.utils.json.lambda

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

class LambdaExceptionDeserializer(
	val objectMapper: ObjectMapper
) : StdDeserializer<LambdaException>(null as Class<*>?)
{
	override fun deserialize(parser: JsonParser, context: DeserializationContext): LambdaException
	{
		val node: JsonNode = parser.codec.readTree(parser)
		
		return LambdaException()
			.apply {
				errorMessage = node["errorMessage"].asText()
				errorType = node["errorType"]?.asText() ?: "unknown"
				stackTrace = node["stackTrace"]?.map { it.asText() } ?: emptyList()
				@Suppress("USELESS_CAST") // cast is not useless, required to use the nullable.toString() function
				cause = objectMapper.readValue((node["cause"] as JsonNode?).toString(), LambdaException::class.java)
			}
	}
}
