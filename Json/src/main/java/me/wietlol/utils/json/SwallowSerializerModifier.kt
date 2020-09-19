package me.wietlol.utils.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.SerializationConfig
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier

class SwallowSerializerModifier(
	val onError: (ex: Throwable, typeName: String, propertyName: String) -> Unit,
) : BeanSerializerModifier()
{
	override fun changeProperties(config: SerializationConfig?, beanDesc: BeanDescription?, beanProperties: List<BeanPropertyWriter>): List<BeanPropertyWriter> =
		beanProperties
			.asSequence()
			.map { property ->
				object : BeanPropertyWriter(property)
				{
					override fun serializeAsField(bean: Any, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider)
					{
						runCatching {
							super.serializeAsField(bean, jsonGenerator, serializerProvider)
						}
							.onFailure { ex ->
								onError(ex, bean.javaClass.typeName, property.name)
							}
					}
				}
			}.toList()
}
