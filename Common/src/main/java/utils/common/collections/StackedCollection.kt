package utils.common.collections

import java.util.*
import kotlin.collections.ArrayList

class StackedCollection<T>(
	private val head: StackedCollection<T>?,
	val items: MutableCollection<T>
) : Collection<T>
{
	constructor() : this(null, ArrayList())
	constructor(items: MutableCollection<T>) : this(null, items)
	
	fun push(items: MutableCollection<T>): StackedCollection<T> =
		StackedCollection(
			this,
			items
		)
	
	fun pop(): StackedCollection<T>? =
		head
	
	override val size: Int
		get() = items.size + (head?.size ?: 0)
	
	override fun contains(element: T): Boolean =
		(items.contains(element)
			|| head?.contains(element)
			?: false)
	
	override fun containsAll(elements: Collection<T>): Boolean =
		elements.all { this.contains(it) }
	
	override fun isEmpty(): Boolean =
		items.isEmpty() && (head?.isEmpty() ?: true)
	
	override fun iterator(): Iterator<T>
	{
		return MultiIterator(ArrayDeque<Iterator<T>>()
			.also { it.push(items.iterator()) }
			.also { if (head != null) it.push(head.iterator()) })
	}
	
	private class MultiIterator<T>(
		val iterators: Queue<Iterator<T>>
	) : Iterator<T>
	{
		var currentIterator: Iterator<T>? = iterators.poll()
		
		override fun hasNext(): Boolean
		{
			while (true)
			{
				val currentIterator = currentIterator
					?: return false
				
				if (currentIterator.hasNext())
					return true
				
				this.currentIterator = iterators.poll()
			}
		}
		
		override fun next(): T =
			currentIterator!!.next()
	}
}
