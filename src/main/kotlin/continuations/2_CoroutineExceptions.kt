package continuations

import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    displayQuote(listOf(4_000, 3_000, 2_000, -1))
}

private fun displayQuote(args: List<Int>) = runBlocking(Default) {
    args
        .map {
            async { download(it) }
        }
        .forEach { println(it.await()) }
}

private suspend fun download(arg: Int): String {
    if (arg <= 0) throw Exception()
    /* do long running work */
    Thread.sleep(arg.toLong())
    println("$arg: Thread ${Thread.currentThread().name}")
    return "THE QUOTE $arg"
}
