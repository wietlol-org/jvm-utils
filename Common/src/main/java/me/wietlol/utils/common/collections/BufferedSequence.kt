package me.wietlol.utils.common.collections

class BufferedSequence<out T>(
	sourceSequence: Sequence<T>
) : Sequence<T>
{
	private val sourceIterator = sourceSequence.iterator()
	private val buffer = mutableListOf<T>()
	
	override fun iterator(): Iterator<T> =
		BufferedSequenceIterator(this)
	
	fun hasIndex(index: Int): Boolean
	{
		while (buffer.size <= index)
		{
			if (sourceIterator.hasNext())
				buffer.add(sourceIterator.next())
			else
				return false
		}
		return true
	}
	
	fun itemAt(index: Int): T =
		buffer.getOrNull(index)
			?: throw NoSuchElementException()
}

fun <T> Sequence<T>.buffered() =
	BufferedSequence(this)
