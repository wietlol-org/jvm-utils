package me.wietlol.utils.common

import org.junit.Test

class JsonBuilder
{
	@Test
	fun createJsonStringFunctions()
	{
		/*
		- Sequence<^>
		- Iterable<^>
		- Array<^>
		- Map<Char, ^>
		- Map<CharSequence, ^>
		*/
		
		val jsonTypes = listOf(
			"Jsonable",
			"Boolean",
			"Byte",
			"Short",
			"Int",
			"Long",
			"Float",
			"Double",
			"Char",
			"CharSequence",
			"UUID",
		)
		
		val iterableTypes = listOf(
			"Sequence",
			"Iterable",
			"Array",
		)
			.flatMap { arr ->
				jsonTypes.map { Pair(arr, it) }
			}
		
		val keyTypes = listOf(
			"Char",
			"CharSequence",
			"Jsonable",
		)
		
//		iterableTypes.forEach { (arr, element) ->
//			println()
//			println("@JvmName(\"toJson$arr$element\")")
//			println("fun $arr<$element?>?.toJsonString(): String =")
//			println("\tthis")
//			println("\t\t?.joinToString(\",\") { it.toJsonString() }")
//			println("\t\t?.surroundWith(\"[\",\"]\")")
//			println("\t\t?: \"null\"")
//		}
		
		jsonTypes.forEach { valueType ->
			keyTypes.forEach { keyType ->
				println()
				println("@JvmName(\"toJsonMap$keyType$valueType\")")
				println("fun Map<out $keyType?, $valueType?>?.toJsonString(): String =")
				println("\tthis")
				println("\t\t?.map { \"\${it.key.toJsonString()}:\${it.value.toJsonString()}\" }")
				println("\t\t?.joinToString(\",\")")
				println("\t\t?.surroundWith(\"{\",\"}\")")
				println("\t\t?: \"null\"")
			}
		}
	}
}
