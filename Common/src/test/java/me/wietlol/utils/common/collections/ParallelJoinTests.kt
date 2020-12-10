package me.wietlol.utils.common.collections

import org.junit.Test
import java.util.Comparator

class ParallelJoinTests
{
	@Test
	fun `sequence join`()
	{
		val left = sequenceOf(1, 2, 5, 6, 7, 9)
		val right = sequenceOf(2, 3, 4, 5, 7, 8, 9)
		
		val mapper = ParallelJoinMapper(Comparator.naturalOrder<Int>())
		
		val result = mapper.outerJoin(left, right)
		
		result.forEach {
			println(it)
		}
	}
	
	@Test
	fun `sequence join with empty left`()
	{
		val left = sequenceOf<Int>()
		val right = sequenceOf(2, 3, 4, 5, 7, 8, 9)
		
		val mapper = ParallelJoinMapper(Comparator.naturalOrder<Int>())
		
		val result = mapper.outerJoin(left, right)
		
		result.forEach {
			println(it)
		}
	}
	
	@Test
	fun `sequence join with empty right`()
	{
		val left = sequenceOf(1, 2, 5, 6, 7, 9)
		val right = sequenceOf<Int>()
		
		val mapper = ParallelJoinMapper(Comparator.naturalOrder<Int>())
		
		val result = mapper.outerJoin(left, right)
		
		result.forEach {
			println(it)
		}
	}
	
	@Test
	fun `sequence join with empty input`()
	{
		val left = sequenceOf<Int>()
		val right = sequenceOf<Int>()
		
		val mapper = ParallelJoinMapper(Comparator.naturalOrder<Int>())
		
		val result = mapper.outerJoin(left, right)
		
		result.forEach {
			println(it)
		}
	}
	
	@Test
	fun bar()
	{
		val left = listOf(1, 2, 5, 6, 7, 9)
		val right = listOf(2, 3, 4, 5, 7, 8, 9)
		
		val mapper = ParallelJoinMapper(Comparator.naturalOrder<Int>())
		
		val result = mapper.outerJoin(left, right)
		
		result.forEach {
			println(it)
		}
		result.forEach {
			println(it)
		}
	}
}
