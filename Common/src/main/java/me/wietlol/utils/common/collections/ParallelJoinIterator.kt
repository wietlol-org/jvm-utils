package me.wietlol.utils.common.collections

import java.util.Comparator

class ParallelJoinIterator<T>(
	val comparator: Comparator<T>,
	val leftIterator: Iterator<T>,
	val rightIterator: Iterator<T>,
	private var left: T?,
	private var right: T?,
) : Iterator<Pair<T?, T?>>
{
	private var leftEnded: Boolean = false
	private var rightEnded: Boolean = false
	
	override fun hasNext(): Boolean =
		!leftEnded || !rightEnded
	
	override fun next(): Pair<T?, T?>
	{
		if (leftEnded)
		// automatically throws when right also ended
			return Pair(null, rightIterator.next())
		if (rightEnded)
			return Pair(leftIterator.next(), null)
		
		val difference = comparator.compare(left, right)
		when
		{
			difference == 0 ->
			{
				val result = Pair(left, right)
				if (leftIterator.hasNext())
					left = leftIterator.next()
				else
					leftEnded = true
				if (rightIterator.hasNext())
					right = rightIterator.next()
				else
					rightEnded = true
				return result
			}
			difference < 0 ->
			{
				val result = Pair(left, null)
				if (leftIterator.hasNext())
					left = leftIterator.next()
				else
					leftEnded = true
				return result
			}
			else ->
			{
				val result = Pair(null, right)
				if (rightIterator.hasNext())
					right = rightIterator.next()
				else
					rightEnded = true
				return result
			}
		}
	}
}
