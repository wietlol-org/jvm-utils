package me.wietlol.utils.common.collections

class ImmutableManyValuesMap<K, V>(
	data: Map<out K, Collection<V>>
) : ManyValuesMap<K, V>
{
	private val data: Map<K, Collection<V>> = HashMap(data)
	
	override val size: Int
		get() = data
			.values
			.asSequence()
			.map { it.size }
			.sum()
	
	override fun isEmpty(): Boolean =
		data.isEmpty()
	
	override fun containsKey(key: K): Boolean =
		data.containsKey(key)
	
	override fun containsValue(value: Collection<V>): Boolean =
		data.containsValue(value)
	
	override fun containsElement(value: V): Boolean =
		data
			.values
			.asSequence()
			.flatMap { it.asSequence() }
			.filter { value == it }
			.any()
	
	override fun getAny(key: K): V? =
		this[key].firstOrNull()
	
	override fun get(key: K): Collection<V> =
		data[key] ?: emptySet()
	
	override val keys: Set<K>
		get() = data.keys
	
	override val values: Collection<Collection<V>>
		get() = data.values
	
	override val entries: Set<Map.Entry<K, Collection<V>>>
		get() = data.entries
}
