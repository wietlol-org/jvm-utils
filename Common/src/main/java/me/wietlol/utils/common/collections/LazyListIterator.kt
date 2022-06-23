package me.wietlol.utils.common.collections

class LazyListIterator<E>(
	val list: LazyList<E>,
	startIndex: Int
) : MutableListIterator<E>
{
	private var index = startIndex - 1
	
	override fun add(element: E) =
		list.add(index, element)
	
	override fun hasNext(): Boolean =
		list.compareCountTo(index + 1) > 0
	
	override fun hasPrevious(): Boolean =
		index >= 0
	
	override fun next(): E =
		list[++index]
	
	override fun nextIndex(): Int =
		index + 1
	
	override fun previous(): E =
		list[index--]
	
	override fun previousIndex(): Int =
		index
	
	override fun remove()
	{
		list.removeAt(index)
	}
	
	override fun set(element: E)
	{
		list[index] = element
	}
}
