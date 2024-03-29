package utils.json.lambda

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

class LambdaExceptionDeserializer : StdDeserializer<LambdaException>(null as Class<*>?)
{
	override fun deserialize(parser: JsonParser, context: DeserializationContext): LambdaException
	{
		val objectMapper = parser.codec as ObjectMapper
		val node: JsonNode = objectMapper.readTree(parser)
		
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
