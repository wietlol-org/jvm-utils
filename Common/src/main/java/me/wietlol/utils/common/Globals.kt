@file:Suppress("unused")

package me.wietlol.utils.common

import me.wietlol.utils.common.collections.ImmutableManyValuesMap
import me.wietlol.utils.common.collections.ManyValuesMap
import java.util.*

fun <T> Sequence<T>.prepend(value: T): Sequence<T> =
	sequenceOf(value) + this

fun <T> Sequence<T>.append(value: T): Sequence<T> =
	this + sequenceOf(value)

fun String.prepend(value: CharSequence): String =
	"$value$this"

fun String.append(value: CharSequence): String =
	"$this$value"

fun String.surroundWith(prefix: CharSequence, postfix: CharSequence = prefix): String =
	"$prefix$this$postfix"

fun <K, V> emptyManyValuesMap(): ManyValuesMap<K, V> = ImmutableManyValuesMap(emptyMap())

fun <K, V> manyValuesMapOf(vararg pairs: Pair<K, V>): ManyValuesMap<K, V> = ImmutableManyValuesMap(
	hashMapOf(*pairs
		.map { Pair(it.first, listOf(it.second)) }
		.toTypedArray()
	)
)

fun <T> T.recursiveLet(mapper: (T) -> T?): Sequence<T>
{
	val root = this
	return sequence {
		var current: T? = root
		while (current != null)
		{
			yield(current)
			current = mapper(current)
		}
	}
}

// https://en.wikipedia.org/wiki/Tree_traversal
fun <T> Sequence<T>.recursiveMapPreOrder(mapper: (T) -> Sequence<T>): Sequence<T> =
	sequence {
		forEach {
			yield(it)
			
			yieldAll(it.let(mapper).recursiveMapPreOrder(mapper))
		}
	}

fun <T> Sequence<T>.recursiveMapPostOrder(mapper: (T) -> Sequence<T>): Sequence<T> =
	sequence {
		forEach {
			yieldAll(it.let(mapper).recursiveMapPostOrder(mapper))
			
			yield(it)
		}
	}

fun <T> Sequence<T>.recursiveMapBreathFirst(mapper: (T) -> Sequence<T>): Sequence<T> =
	sequence {
		val queue = ArrayDeque<T>()
		queue.addAll(this@recursiveMapBreathFirst)
		
		while (queue.isNotEmpty())
		{
			val element = queue.remove()
			yield(element)
			
			queue.addAll(mapper(element))
		}
	}

fun Any?.toNullString() =
	this?.toString() ?: "null"

fun <T> T?.asCollection(): Set<T> =
	if (this == null)
		Collections.emptySet()
	else
		Collections.singleton(this)

inline fun <reified T> Any.cast(): T =
	this as T
