package utils.common.streams

import utils.common.readSignedVarInt
import utils.common.readSignedVarLong
import utils.common.readUnsignedVarInt
import utils.common.readUnsignedVarLong
import utils.common.writeSignedVarInt
import utils.common.writeSignedVarLong
import utils.common.writeUnsignedVarInt
import utils.common.writeUnsignedVarLong
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.util.*
import kotlin.experimental.and

/*
 * Writing
 */

fun OutputStream.writeString(data: String, charset: Charset)
{
	val bytes = data.toByteArray(charset)
	writeUnsignedVarInt(bytes.size)
	write(bytes)
}

fun OutputStream.writeInt(data: Int)
{
	val bytes = ByteBuffer.allocate(4).putInt(data).array()
	write(bytes)
}

fun OutputStream.writeFloat(data: Float)
{
	val bytes = ByteBuffer.allocate(4).putFloat(data).array()
	write(bytes)
}

fun OutputStream.writeLong(data: Long)
{
	val bytes = ByteBuffer.allocate(8).putLong(data).array()
	write(bytes)
}

fun OutputStream.writeDouble(data: Double)
{
	val bytes = ByteBuffer.allocate(8).putDouble(data).array()
	write(bytes)
}

fun OutputStream.writeShort(data: Short)
{
	val bytes = ByteBuffer.allocate(2).putShort(data).array()
	write(bytes)
}

fun OutputStream.writeChar(data: Char)
{
	val bytes = ByteBuffer.allocate(1).putChar(data).array()
	write(bytes)
}

fun OutputStream.writeByte(data: Byte)
{
	val bytes = ByteBuffer.allocate(1).put(data).array()
	write(bytes)
}

fun OutputStream.writeBoolean(flag: Boolean)
{
	writeByte((if (flag) 1 else 0).toByte())
}

fun OutputStream.writeUuid(data: UUID)
{
	writeLong(data.mostSignificantBits)
	writeLong(data.leastSignificantBits)
}

fun OutputStream.writeSignedVarInt(data: Int)
{
	writeSignedVarInt(data, DataOutputStream(this))
}

fun OutputStream.writeUnsignedVarInt(data: Int)
{
	writeUnsignedVarInt(data, DataOutputStream(this))
}

fun OutputStream.writeSignedVarLong(data: Long)
{
	writeSignedVarLong(data, DataOutputStream(this))
}

fun OutputStream.writeUnsignedVarLong(data: Long)
{
	writeUnsignedVarLong(data, DataOutputStream(this))
}

/*
 * Reading
 */

fun InputStream.readString(charset: Charset): String =
	String(scrap(readUnsignedVarInt()), charset)

fun InputStream.readInt(): Int =
	ByteBuffer.wrap(scrap(4)).int

fun InputStream.readFloat(): Float =
	ByteBuffer.wrap(scrap(4)).float

fun InputStream.readLong(): Long =
	ByteBuffer.wrap(scrap(8)).long

fun InputStream.readDouble(): Double =
	ByteBuffer.wrap(scrap(8)).double

fun InputStream.readShort(): Short =
	ByteBuffer.wrap(scrap(2)).short

fun InputStream.readByte(): Byte =
	scrap(1)[0]

fun InputStream.readChar(): Char =
	ByteBuffer.wrap(scrap(1)).char

fun InputStream.readBoolean(): Boolean
{
	val bytes = scrap(1)
	return bytes[0] and 0x01 != 0.toByte()
}

fun InputStream.readUuid(): UUID =
	UUID(readLong(), readLong())

fun InputStream.readSignedVarInt(): Int =
	readSignedVarInt(DataInputStream(this))

fun InputStream.readUnsignedVarInt(): Int =
	readUnsignedVarInt(DataInputStream(this))

fun InputStream.readSignedVarLong(): Long =
	readSignedVarLong(DataInputStream(this))

fun InputStream.readUnsignedVarLong(): Long =
	readUnsignedVarLong(DataInputStream(this))

private fun InputStream.scrap(length: Int): ByteArray
{
	val bytes = ByteArray(length)
	if (read(bytes) > -1)
		return bytes
	throw IOException("End of stream reached.")
}
