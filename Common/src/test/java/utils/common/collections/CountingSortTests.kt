package utils.common.collections

import org.junit.Test

class CountingSortTests
{
	@Test
	fun `assert that it works`()
	{
		val input = listOf(1, 2, 3)
		
		val sorter = CountingSort()
		
		val result = sorter.sort(input)
		
		result.forEach { println(it) }
	}
	
	@Test
	fun `assert that it works on an unsorted input`()
	{
		val input = listOf(35, 21, 27, 12)
		
		val sorter = CountingSort()
		
		val result = sorter.sort(input)
		
		result.forEach { println(it) }
	}
	
	@Test
	fun `assert that it works on an empty input`()
	{
		val input = emptyList<Int>()
		
		val sorter = CountingSort()
		
		val result = sorter.sort(input)
		
		result.forEach { println(it) }
	}
	
	@Test
	fun `assert that it works on negative input`()
	{
		val input = listOf(5, -5, 0, 2, -2)
		
		val sorter = CountingSort()
		
		val result = sorter.sort(input)
		
		result.forEach { println(it) }
	}
	
	@Test
	fun `assert that it works on duplicate input`()
	{
		val input = listOf(1, 5, 1, 5, 1, 5, 1, 5, 1)
		
		val sorter = CountingSort()
		
		val result = sorter.sort(input)
		
		result.forEach { println(it) }
	}
}
