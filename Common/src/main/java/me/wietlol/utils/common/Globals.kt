package me.wietlol.utils.common

import me.wietlol.utils.common.collections.ImmutableManyValuesMap
import me.wietlol.utils.common.collections.ManyValuesMap

fun <T> Sequence<T>.prepend(value: T) =
	sequenceOf(value) + this

fun <T> Sequence<T>.append(value: T) =
	this + sequenceOf(value)

fun <K, V> emptyManyValuesMap(): ManyValuesMap<K, V> = ImmutableManyValuesMap(emptyMap())

fun <K, V> manyValuesMapOf(vararg pairs: Pair<K, V>): ManyValuesMap<K, V> = ImmutableManyValuesMap(
	hashMapOf(*pairs
		.map { Pair(it.first, listOf(it.second)) }
		.toTypedArray()
	)
)
