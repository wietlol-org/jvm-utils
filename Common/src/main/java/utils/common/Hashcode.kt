package utils.common

typealias HashCode = Int

private const val hashStep = 31

const val emptyHashCode: HashCode = 0

private fun HashCode.step(): HashCode =
	this * hashStep

fun HashCode.with(value: Byte): HashCode =
	step() + value.hashCode()

fun HashCode.with(value: Short): HashCode =
	step() + value.hashCode()

fun HashCode.with(value: Int): HashCode =
	step() + value.hashCode()

fun HashCode.with(value: Long): HashCode =
	step() + value.hashCode()

fun HashCode.with(value: Float): HashCode =
	step() + value.hashCode()

fun HashCode.with(value: Double): HashCode =
	step() + value.hashCode()

fun HashCode.with(value: Char): HashCode =
	step() + value.hashCode()

fun HashCode.with(value: Any?): HashCode =
	step() + value.hashCode()

fun HashCode.with(value: Byte?): HashCode =
	step() + value.hashCode()
