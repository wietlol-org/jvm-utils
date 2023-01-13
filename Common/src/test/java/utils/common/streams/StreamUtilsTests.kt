package utils.common.streams

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.ByteArrayOutputStream

class StreamUtilsTests
{
	@Test
	fun foo()
	{
		val expected = 123456
		
		val data = ByteArrayOutputStream().use {
			it.writeUnsignedVarInt(expected)
			it.toByteArray()
		}
		
		data.forEach {
			println(it.toUByte().toString())
		}
		
		val actual = data.inputStream().use {
			it.readUnsignedVarInt()
		}
		
		assertThat(actual).isEqualTo(expected)
	}
}
