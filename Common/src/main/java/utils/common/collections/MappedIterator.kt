package utils.common.collections

class MappedIterator<I, O>(
	val iterator: Iterator<I>,
	val mapper: (I) -> O,
) : Iterator<O>
{
	override fun hasNext(): Boolean =
		iterator.hasNext()
	
	override fun next(): O =
		mapper(iterator.next())
}
