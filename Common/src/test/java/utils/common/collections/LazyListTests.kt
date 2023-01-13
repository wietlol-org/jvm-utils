package utils.common.collections

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LazyListTests
{
	@Test
	fun `assert that null values in a lazy list do not throw an exception`()
	{
		val list = LazyList(listOf("", null, ""))
		
		val x = list.contains("a")
		val y = list.contains(null)
		
		assertThat(x).isFalse
		assertThat(y).isTrue
	}
	
	@Test
	fun `assert that lazy list iterator has correct indices while iterating`()
	{
//		val list = listOf("a", "b", "c")
		val list = LazyList(listOf("a", "b", "c"))
		
		val iterator = list.listIterator()
		
		assertThat(iterator.hasNext()).isEqualTo(true)
		assertThat(iterator.nextIndex()).isEqualTo(0)
		assertThat(iterator.hasPrevious()).isEqualTo(false)
		assertThat(iterator.previousIndex()).isEqualTo(-1)
		
		assertThat(iterator.next()).isEqualTo("a")
		assertThat(iterator.hasNext()).isEqualTo(true)
		assertThat(iterator.nextIndex()).isEqualTo(1)
		assertThat(iterator.hasPrevious()).isEqualTo(true)
		assertThat(iterator.previousIndex()).isEqualTo(0)
		
		assertThat(iterator.next()).isEqualTo("b")
		assertThat(iterator.hasNext()).isEqualTo(true)
		assertThat(iterator.nextIndex()).isEqualTo(2)
		assertThat(iterator.hasPrevious()).isEqualTo(true)
		assertThat(iterator.previousIndex()).isEqualTo(1)
		
		assertThat(iterator.previous()).isEqualTo("b")
		assertThat(iterator.hasNext()).isEqualTo(true)
		assertThat(iterator.nextIndex()).isEqualTo(1)
		assertThat(iterator.hasPrevious()).isEqualTo(true)
		assertThat(iterator.previousIndex()).isEqualTo(0)
	}
	
	@Test
	fun `assert that lazy list toString has readable format`()
	{
//		val list = listOf("a", "b", "c")
		val list = LazyList(listOf("a", "b", "c"))
	
		assertThat(list.toString()).isEqualTo("[a, b, c]")
	}
	
	@Test
	fun `assert that removeAll works`()
	{
		val list = LazyList(listOf("a", "b", "c"))
		
		list.removeAll(listOf("a", "c"))
		
		assertThat(list).hasSize(1)
		assertThat(list[0]).isEqualTo("b")
	}
	
	@Test
	fun `assert that retainAll works`()
	{
		val list = LazyList(listOf("a", "b", "c"))
		
		list.retainAll(listOf("b"))
		
		assertThat(list).hasSize(1)
		assertThat(list[0]).isEqualTo("b")
	}
	
	@Test
	fun `assert that stuff works`()
	{
		val list = LazyList(listOf("a", "b", "c"))
		
		list.removeAll(listOf("a", "c"))
		
		assertThat(list).hasSize(1)
		assertThat(list[0]).isEqualTo("b")
	}
}
