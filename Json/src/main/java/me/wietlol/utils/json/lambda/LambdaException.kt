package me.wietlol.utils.json.lambda

class LambdaException : Exception()
{
	lateinit var errorMessage: String
	lateinit var errorType: String
	lateinit var stackTrace: List<String>
	override var cause: LambdaException? = null
	
	override val message: String
		get() = "$errorType: $errorMessage" + stackTrace.map { "\r\n\tat $it" }
			.let {
				if (cause != null)
					it + "\r\n${cause!!.message}"
				else
					it
			}
}
