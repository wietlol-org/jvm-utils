@file:Suppress("unused")

package me.wietlol.utils.common

import java.util.*

/*
jsonables:
	- <null>
	- Jsonable
	- Boolean
	- Byte
	- Short
	- Int
	- Long
	- Float
	- Double
	- Char
	- CharSequence
	- UUID
	- Sequence<^>
	- Iterable<^>
	- Array<^>
	- Map<Char, ^>
	- Map<CharSequence, ^>
 */

fun Jsonable?.toJsonString(): String =
	this?.toJson() ?: "null"

fun Boolean?.toJsonString(): String =
	this?.toString() ?: "null"

fun Byte?.toJsonString(): String =
	this?.toString() ?: "null"

fun Short?.toJsonString(): String =
	this?.toString() ?: "null"

fun Int?.toJsonString(): String =
	this?.toString() ?: "null"

fun Long?.toJsonString(): String =
	this?.toString() ?: "null"

fun Float?.toJsonString() =
	this?.toString() ?: "null"

fun Double?.toJsonString() =
	this?.toString() ?: "null"

fun UUID?.toJsonString() =
	if (this == null)
		"null"
	else
		"\"$this\""

fun Char?.toJsonString() =
	if (this == null)
		"null"
	else
		"\"$this\""

fun CharSequence?.toJsonString() =
	if (this == null)
		"null"
	else
		"\"${
			toString()
				.replace("\\", "\\\\")
				.replace("\r", "\\r")
				.replace("\n", "\\n")
				.replace("\"", "\\\"")
		}\""

@JvmName("toJsonCollection2")
fun Iterable<Iterable<CharSequence?>?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[", "]")
		?: "null"

@JvmName("toJsonCollection4")
fun Iterable<Iterable<Jsonable?>?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[", "]")
		?: "null"

// generated code:

@JvmName("toJsonSequenceJsonable")
fun Sequence<Jsonable?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceBoolean")
fun Sequence<Boolean?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceByte")
fun Sequence<Byte?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceShort")
fun Sequence<Short?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceInt")
fun Sequence<Int?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceLong")
fun Sequence<Long?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceFloat")
fun Sequence<Float?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceDouble")
fun Sequence<Double?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceChar")
fun Sequence<Char?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceCharSequence")
fun Sequence<CharSequence?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonSequenceUUID")
fun Sequence<UUID?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableJsonable")
fun Iterable<Jsonable?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableBoolean")
fun Iterable<Boolean?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableByte")
fun Iterable<Byte?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableShort")
fun Iterable<Short?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableInt")
fun Iterable<Int?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableLong")
fun Iterable<Long?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableFloat")
fun Iterable<Float?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableDouble")
fun Iterable<Double?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableChar")
fun Iterable<Char?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableCharSequence")
fun Iterable<CharSequence?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonIterableUUID")
fun Iterable<UUID?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayJsonable")
fun Array<Jsonable?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayBoolean")
fun Array<Boolean?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayByte")
fun Array<Byte?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayShort")
fun Array<Short?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayInt")
fun Array<Int?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayLong")
fun Array<Long?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayFloat")
fun Array<Float?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayDouble")
fun Array<Double?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayChar")
fun Array<Char?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayCharSequence")
fun Array<CharSequence?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonArrayUUID")
fun Array<UUID?>?.toJsonString(): String =
	this
		?.joinToString(",") { it.toJsonString() }
		?.surroundWith("[","]")
		?: "null"

@JvmName("toJsonMapCharJsonable")
fun Map<out Char?, Jsonable?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceJsonable")
fun Map<out CharSequence?, Jsonable?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableJsonable")
fun Map<out Jsonable?, Jsonable?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharBoolean")
fun Map<out Char?, Boolean?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceBoolean")
fun Map<out CharSequence?, Boolean?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableBoolean")
fun Map<out Jsonable?, Boolean?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharByte")
fun Map<out Char?, Byte?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceByte")
fun Map<out CharSequence?, Byte?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableByte")
fun Map<out Jsonable?, Byte?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharShort")
fun Map<out Char?, Short?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceShort")
fun Map<out CharSequence?, Short?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableShort")
fun Map<out Jsonable?, Short?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharInt")
fun Map<out Char?, Int?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceInt")
fun Map<out CharSequence?, Int?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableInt")
fun Map<out Jsonable?, Int?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharLong")
fun Map<out Char?, Long?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceLong")
fun Map<out CharSequence?, Long?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableLong")
fun Map<out Jsonable?, Long?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharFloat")
fun Map<out Char?, Float?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceFloat")
fun Map<out CharSequence?, Float?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableFloat")
fun Map<out Jsonable?, Float?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharDouble")
fun Map<out Char?, Double?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceDouble")
fun Map<out CharSequence?, Double?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableDouble")
fun Map<out Jsonable?, Double?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharChar")
fun Map<out Char?, Char?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceChar")
fun Map<out CharSequence?, Char?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableChar")
fun Map<out Jsonable?, Char?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharCharSequence")
fun Map<out Char?, CharSequence?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceCharSequence")
fun Map<out CharSequence?, CharSequence?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableCharSequence")
fun Map<out Jsonable?, CharSequence?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharUUID")
fun Map<out Char?, UUID?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapCharSequenceUUID")
fun Map<out CharSequence?, UUID?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"

@JvmName("toJsonMapJsonableUUID")
fun Map<out Jsonable?, UUID?>?.toJsonString(): String =
	this
		?.map { "${it.key.toJsonString()}:${it.value.toJsonString()}" }
		?.joinToString(",")
		?.surroundWith("{","}")
		?: "null"
