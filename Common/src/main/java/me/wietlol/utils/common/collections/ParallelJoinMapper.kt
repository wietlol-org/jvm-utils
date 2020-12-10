package me.wietlol.utils.common.collections

import java.util.Comparator

class ParallelJoinMapper<T>(
	val comparator: Comparator<T>,
)
{
	fun outerJoin(left: Iterable<T>, right: Iterable<T>): Iterable<Pair<T?, T?>> =
		WrappedIterable {
			val leftIterator = left.iterator()
			val rightIterator = right.iterator()
			
			if (leftIterator.hasNext().not())
				MappedIterator(rightIterator) { Pair(null, it) }
			if (rightIterator.hasNext().not())
				MappedIterator(leftIterator) { Pair(it, null) }
			else
				ParallelJoinIterator(comparator, leftIterator, rightIterator, leftIterator.next(), rightIterator.next())
		}
	
	fun outerJoin(left: Sequence<T>, right: Sequence<T>): Sequence<Pair<T?, T?>>
	{
		val leftIterator = left.iterator()
		if (leftIterator.hasNext().not())
			return right.map { Pair(null, it) }
		
		val rightIterator = right.iterator()
		if (rightIterator.hasNext().not())
			return left.map { Pair(it, null) }
		
		return sequence {
			var l = leftIterator.next()
			var r = rightIterator.next()
			
			while (true)
			{
				val difference = comparator.compare(l, r)
				
				if (difference == 0)
				{
					yield(Pair(l, r))
					if (leftIterator.hasNext())
						l = leftIterator.next()
					else
						break
					if (rightIterator.hasNext())
						r = rightIterator.next()
					else
						break
				}
				else if (difference < 0)
				{
					yield(Pair(l, null))
					if (leftIterator.hasNext())
						l = leftIterator.next()
					else
						break
				}
				else
				{
					yield(Pair(null, r))
					if (rightIterator.hasNext())
						r = rightIterator.next()
					else
						break
				}
			}
			
			while (leftIterator.hasNext())
				yield(Pair(leftIterator.next(), null))
			
			while (rightIterator.hasNext())
				yield(Pair(null, rightIterator.next()))
		}
	}
}
