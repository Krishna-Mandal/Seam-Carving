// Do not change the code below

interface ILogHolder {
    fun printLog()
    fun collectLog(log: String)
    var curLog: String
}

class Logger : ILogHolder {
    override fun printLog() {
        println(curLog)
    }

    override fun collectLog(log: String) {
        curLog += "\n> $log"
    }

    override var curLog: String = "> Logger initialized."
}
// Do not change the code above

// Introduce the delegate here, following the argument declaration
class ErrorHandler(base: ILogHolder) : ILogHolder by base {
    var errorMessage: String = ""
    fun getErrorMessage(msg: String) {
        errorMessage = msg
        logErrorMessage()
    }

    fun logErrorMessage() {
        collectLog(errorMessage)
    }
}

fun doSomething(handler: ErrorHandler) {
    handler.getErrorMessage("Access denied")
    handler.getErrorMessage("Out of memory")
    handler.getErrorMessage("I'm not an error")
}

fun main() {
    val log = Logger()
    val handler = ErrorHandler(log)

    doSomething(handler)

    handler.printLog()
}
