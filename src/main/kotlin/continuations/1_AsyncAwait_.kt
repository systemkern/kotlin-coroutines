package continuations

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    displayQuote()
}

private fun displayQuote() = runBlocking {
    val quote = async { download() }.await()
    println(quote)
}

private fun download(arg: String = ""): String {
    /* do long running work */
    return "THE QUOTE $arg"
}
