package me.wietlol.utils.common

import java.io.DataInput
import java.io.DataOutput
import java.io.IOException

/**
 * Encodes a value using the variable-length encoding from
 * [
    * Google Protocol Buffers](http://code.google.com/apis/protocolbuffers/docs/encoding.html). It uses zig-zag encoding to efficiently
 * encode signed values. If values are known to be non-negative,
 * [.writeUnsignedVarLong] should be used.
 *
 * @param value value to encode
 * @param out   to writeObject bytes to
 * @throws IOException if [DataOutput] throws [IOException]
 */
@Throws(IOException::class)
fun writeSignedVarLong(value: Long, out: DataOutput)
{
	// Great trick from http://code.google.com/apis/protocolbuffers/docs/encoding.html#types
	writeUnsignedVarLong(value shl 1 xor (value shr 63), out)
}

/**
 * Encodes a value using the variable-length encoding from
 * [
    * Google Protocol Buffers](http://code.google.com/apis/protocolbuffers/docs/encoding.html). Zig-zag is not used, so input must not be negative.
 * If values can be negative, use [.writeSignedVarLong]
 * instead. This method treats negative input as like a large unsigned value.
 *
 * @param value value to encode
 * @param out   to writeObject bytes to
 * @throws IOException if [DataOutput] throws [IOException]
 */
@Throws(IOException::class)
fun writeUnsignedVarLong(value: Long, out: DataOutput)
{
	var value = value
	while ((value and -0x80L) != 0L)
	{
		out.writeByte(value.toInt() and 0x7F or 0x80)
		value = value ushr 7
	}
	out.writeByte(value.toInt() and 0x7F)
}

/**
 * @see .writeSignedVarLong
 */
@Throws(IOException::class)
fun writeSignedVarInt(value: Int, out: DataOutput)
{
	// Great trick from http://code.google.com/apis/protocolbuffers/docs/encoding.html#types
	writeUnsignedVarInt(value shl 1 xor (value shr 31), out)
}

/**
 * @see .writeUnsignedVarLong
 */
@Throws(IOException::class)
fun writeUnsignedVarInt(value: Int, out: DataOutput)
{
	var value = value
	while ((value and -0x80) != 0)
	{
		out.writeByte(value and 0x7F or 0x80)
		value = value ushr 7
	}
	out.writeByte(value and 0x7F)
}

fun writeSignedVarInt(value: Int): ByteArray
{
	// Great trick from http://code.google.com/apis/protocolbuffers/docs/encoding.html#types
	return writeUnsignedVarInt(value shl 1 xor (value shr 31))
}

/**
 * @see .writeUnsignedVarLong
 */
fun writeUnsignedVarInt(value: Int): ByteArray
{
	var value = value
	val byteArrayList = ByteArray(10)
	var i = 0
	while ((value and -0x80).toLong() != 0L)
	{
		byteArrayList[i++] = (value and 0x7F or 0x80).toByte()
		value = value ushr 7
	}
	byteArrayList[i] = (value and 0x7F).toByte()
	val out = ByteArray(i + 1)
	while (i >= 0)
	{
		out[i] = byteArrayList[i]
		i--
	}
	return out
}

/**
 * @param in to read bytes from
 * @return decode value
 * @throws IOException              if [DataInput] throws [IOException]
 * @throws IllegalArgumentException if variable-length value does not terminate
 * after 9 bytes have been read
 * @see .writeSignedVarLong
 */
@Throws(IOException::class)
fun readSignedVarLong(`in`: DataInput): Long
{
	val raw = readUnsignedVarLong(`in`)
	// This undoes the trick in writeSignedVarLong()
	val temp = raw shl 63 shr 63 xor raw shr 1
	// This extra step lets us deal with the largest signed values by treating
	// negative results from read unsigned methods as like unsigned values
	// Must re-flip the top bit if the original read value had it set.
	return temp xor (raw and (1L shl 63))
}

/**
 * @param in to read bytes from
 * @return decode value
 * @throws IOException              if [DataInput] throws [IOException]
 * @throws IllegalArgumentException if variable-length value does not terminate
 * after 9 bytes have been read
 * @see .writeUnsignedVarLong
 */
@Throws(IOException::class)
fun readUnsignedVarLong(`in`: DataInput): Long
{
	var value = 0L
	var i = 0
	var b: Long
	
	while (true)
	{
		b = `in`.readByte().toLong()
		
		if (b and 0x80L == 0L)
			break
		
		value = value or (b and 0x7F shl i)
		i += 7
		if (i > 63)
		{
			throw IllegalArgumentException("Variable length quantity is too long")
		}
	}
	return value or (b shl i)
}

/**
 * @throws IllegalArgumentException if variable-length value does not terminate
 * after 5 bytes have been read
 * @throws IOException              if [DataInput] throws [IOException]
 * @see .readSignedVarLong
 */
@Throws(IOException::class)
fun readSignedVarInt(`in`: DataInput): Int
{
	val raw = readUnsignedVarInt(`in`)
	// This undoes the trick in writeSignedVarInt()
	val temp = raw shl 31 shr 31 xor raw shr 1
	// This extra step lets us deal with the largest signed values by treating
	// negative results from read unsigned methods as like unsigned values.
	// Must re-flip the top bit if the original read value had it set.
	return temp xor (raw and (1 shl 31))
}

/**
 * @throws IllegalArgumentException if variable-length value does not terminate
 * after 5 bytes have been read
 * @throws IOException              if [DataInput] throws [IOException]
 * @see .readUnsignedVarLong
 */
@Throws(IOException::class)
fun readUnsignedVarInt(`in`: DataInput): Int
{
	var value = 0
	var i = 0
	var b: Int
	
	while (true)
	{
		b = `in`.readByte().toInt()
		
		if (b and 0x80 == 0)
			break
		
		value = value or (b and 0x7F shl i)
		i += 7
		if (i > 35)
		{
			throw IllegalArgumentException("Variable length quantity is too long")
		}
	}
	return value or (b shl i)
}

fun readSignedVarInt(bytes: ByteArray): Int
{
	val raw = readUnsignedVarInt(bytes)
	// This undoes the trick in writeSignedVarInt()
	val temp = raw shl 31 shr 31 xor raw shr 1
	// This extra step lets us deal with the largest signed values by treating
	// negative results from read unsigned methods as like unsigned values.
	// Must re-flip the top bit if the original read value had it set.
	return temp xor (raw and (1 shl 31))
}

fun readUnsignedVarInt(bytes: ByteArray): Int
{
	var value = 0
	var i = 0
	var rb = Byte.MIN_VALUE.toInt()
	for (b in bytes)
	{
		rb = b.toInt()
		if (rb and 0x80 == 0)
		{
			break
		}
		value = value or (rb and 0x7f shl i)
		i += 7
		if (i > 35)
		{
			throw IllegalArgumentException("Variable length quantity is too long")
		}
	}
	return value or (rb shl i)
}
