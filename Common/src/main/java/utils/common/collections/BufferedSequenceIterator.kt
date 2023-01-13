package utils.common.collections

class BufferedSequenceIterator<out T>(
	val sequence: BufferedSequence<T>
) : Iterator<T>
{
	private var index = -1
	
	override fun hasNext(): Boolean =
		sequence.hasIndex(++index)
	
	override fun next(): T =
		sequence.itemAt(index)
}
