package utils.common.collections

import utils.common.surroundWith

class LazyList<E>(
	values: Iterator<E>
) : AbstractList<E>(), MutableList<E>, List<E>, AutoCloseable
{
	constructor(iterable: Iterable<E>) : this(iterable.iterator())
	constructor(sequence: Sequence<E>) : this(sequence.iterator())
	
	private var values: Iterator<E>? = values
	private val head = ArrayList<E>()
	private val tail = ArrayList<E>()
	
	override val size: Int
		get()
		{
			consumeFeed()
			return head.size
		}
	
	override fun clear()
	{
		head.clear()
		tail.clear()
		closeIterator()
	}
	
	override fun addAll(elements: Collection<E>): Boolean
	{
		var result = false
		elements.forEach {
			add(it)
			result = true
		}
		return result
	}
	
	override fun addAll(index: Int, elements: Collection<E>): Boolean
	{
		var result = false
		elements.forEachIndexed { offset, it ->
			add(index + offset, it)
			result = true
		}
		return result
	}
	
	override fun add(index: Int, element: E)
	{
		assertConsumptionTo(index)
		return head.add(index, element)
	}
	
	override fun add(element: E): Boolean
	{
		consumeFeed()
		return head.add(element)
	}
	
	override fun get(index: Int): E
	{
		assertConsumptionTo(index)
		return head[index]
	}
	
	override fun isEmpty(): Boolean
	{
		if (head.isNotEmpty())
			return false
		if (tail.isNotEmpty())
			return false
		if (takeNext())
			return false
		return true
	}
	
	override fun iterator(): MutableListIterator<E> =
		listIterator()
	
	override fun listIterator(): MutableListIterator<E> =
		listIterator(0)
	
	override fun listIterator(index: Int): MutableListIterator<E> =
		LazyListIterator(this, index)
	
	override fun removeAt(index: Int): E
	{
		assertConsumptionTo(index)
		return head.removeAt(index)
	}
	
	override fun set(index: Int, element: E): E
	{
		assertConsumptionTo(index)
		return head.set(index, element)
	}
	
	override fun retainAll(elements: Collection<E>): Boolean
	{
		if (elements.isEmpty())
		{
			clear()
			return true
		}
		
		val set = elements.toHashSet()
		
		val iterator = listIterator()
		
		var result = false
		while (iterator.hasNext())
		{
			val element = iterator.next()
			if (!set.contains(element))
			{
				iterator.remove()
				result = true
			}
		}
		
		return result
	}
	
	override fun removeAll(elements: Collection<E>): Boolean
	{
		if (elements.isEmpty())
			return false
		
		val set = elements.toHashSet()
		
		val iterator = listIterator()
		
		var result = false
		while (iterator.hasNext())
		{
			val element = iterator.next()
			if (set.contains(element))
			{
				iterator.remove()
				result = true
			}
		}
		
		return result
	}
	
	override fun remove(element: E): Boolean
	{
		var index = 0
		forEach {
			if (it == element)
			{
				head.removeAt(index)
				return true
			}
			index++
		}
		return false
	}
	
	override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> =
		super.subList(fromIndex,  toIndex) as MutableList<E>
	
	override fun lastIndexOf(element: E): Int
	{
		var index = 0
		var foundIndex = -1
		forEach {
			if (it == element)
				foundIndex = index
			index++
		}
		return foundIndex
	}
	
	override fun indexOf(element: E): Int
	{
		var index = 0
		forEach {
			if (it == element)
				return index
			index++
		}
		return -1
	}
	
	override fun containsAll(elements: Collection<E>): Boolean
	{
		var targetsSize = elements.size
		if (targetsSize == 0)
			return true
		
		val set = elements.toHashSet()
		
		forEach {
			if (set.contains(it))
			{
				targetsSize--
				if (targetsSize == 0)
					return true
				set.remove(it)
			}
		}
		
		return false
	}
	
	override fun contains(element: E): Boolean
	{
		forEach {
			if (it == element)
				return true
		}
		return false
	}
	
	override fun close()
	{
		closeIterator()
	}
	
	private fun closeIterator()
	{
		if (values != null)
		{
			head.addAll(tail)
			tail.clear() // shouldn't be necessary, as the tail should now never be used again, but for good sport, we can clear it
			values = null
		}
	}
	
	private fun assertConsumptionTo(targetIndex: Int)
	{
		if (values != null && head.size <= targetIndex)
			consumeFeed()
	}
	
	private fun consumeFeed()
	{
		if (values != null)
			@Suppress("ControlFlowWithEmptyBody") // while (takeNext()); is used to exhaust the values feed
			while (takeNext());
	}
	
	private fun takeNext(): Boolean
	{
		val values = values
		if (values != null && values.hasNext())
		{
			head.add(values.next())
			return true
		}
		
		val result = tail.isNotEmpty()
		closeIterator()
		return result
	}
	
	fun compareCountTo(value: Int): Int
	{
		var remainder = value
		
		// if the head is larger than the value, then is-larger
		val headSize = head.size
		if (headSize > remainder)
			return 1
		remainder -= headSize
		
		// if the head+tail is larger than the value, then is-larger
		val tailSize = tail.size
		if (tailSize > remainder)
			return 1
		remainder -= tailSize
		
		// while remainder is positive, if we can't take more values, then is-smaller
		while (remainder --> 0)
			if (!takeNext())
				return -1
		
		// now that remainder is 0, if we can still take more values, then is-larger
		if (takeNext())
			return 1
		
		// if we can't take more values, then is-equal
		return 0
	}
	
	override fun toString(): String =
		joinToString()
			.surroundWith("[", "]")
}

