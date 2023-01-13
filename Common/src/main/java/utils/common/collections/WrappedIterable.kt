package utils.common.collections

class WrappedIterable<T>(
	val iteratorFactory: () -> Iterator<T>,
) : Iterable<T>
{
	override fun iterator(): Iterator<T> =
		iteratorFactory()
}
