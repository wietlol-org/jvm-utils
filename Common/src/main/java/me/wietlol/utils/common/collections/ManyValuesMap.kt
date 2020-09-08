package me.wietlol.utils.common.collections

interface ManyValuesMap<K, V> : Map<K, Collection<V>>
{
	fun containsElement(value: V): Boolean
	
	fun getAny(key: K): V?
	override operator fun get(key: K): Collection<V>
	
	fun mergeWith(other: ManyValuesMap<K, V>): ManyValuesMap<K, V>
	{
		val map: MutableMap<K, Collection<V>> = HashMap()
		
		this.forEach { (key, value) ->
			map.computeIfAbsent(key) { emptyList() }
				.also { map[key] = it + value }
		}
		
		other.forEach { (key, value) ->
			map.computeIfAbsent(key) { emptyList() }
				.also { map[key] = it + value }
		}
		
		return ImmutableManyValuesMap(map)
	}
}
