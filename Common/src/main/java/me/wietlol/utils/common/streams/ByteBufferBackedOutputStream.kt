package me.wietlol.utils.common.streams

import java.io.OutputStream
import java.nio.ByteBuffer

class ByteBufferBackedOutputStream(
	val buffer: ByteBuffer
) : OutputStream()
{
	override fun write(b: Int)
	{
		buffer.put(b.toByte())
	}
	
	override fun write(bytes: ByteArray?, off: Int, len: Int)
	{
		buffer.put(bytes, off, len)
	}
}
