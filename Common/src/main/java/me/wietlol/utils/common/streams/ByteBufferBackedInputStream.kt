package me.wietlol.utils.common.streams

import java.io.InputStream
import java.nio.ByteBuffer
import kotlin.math.min

class ByteBufferBackedInputStream(
	val buffer: ByteBuffer
) : InputStream()
{
	override fun read(): Int =
		if (buffer.hasRemaining().not())
			-1
		else
			buffer.get().toInt() and 0xFF
	
	override fun read(bytes: ByteArray?, off: Int, len: Int): Int
	{
		if (buffer.hasRemaining().not())
			return -1
		
		val length = min(len, buffer.remaining())
		buffer[bytes, off, length]
		return length
	}
}
