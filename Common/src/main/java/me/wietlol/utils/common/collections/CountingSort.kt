package me.wietlol.utils.common.collections

class CountingSort
{
	fun sort(input: List<Int>): List<Int>
	{
		if (input.isEmpty())
			return emptyList()
		
		var min: Int? = null
		var max: Int? = null
		input.forEach {
			if (min == null || it < min!!)
				min = it
			if (max == null || it > max!!)
				max = it
		}
		
		val difference = max!! - min!!
		val count = Array(difference + 1) { 0 }
		
		input.forEach { count[it - min!!]++ }
		
		val result = ArrayList<Int>(input.count())
		
		count.forEachIndexed { index, it ->
			repeat(it) {
				result.add(index + min!!)
			}
		}
		
		return result
	}
}
