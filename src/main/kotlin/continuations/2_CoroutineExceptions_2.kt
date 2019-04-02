package continuations

import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.management.loading.ClassLoaderRepository

/**
 * This example will show how multiple exceptions are propagated properly through a coroutine scope.
 * Every Invokation of the download function throws an exception, and all those exceptions are
 * propagated to the.
 * The first one as actuall the one that will be thrown,
 * all others are attached as "supressed exceptions"
 */
fun main() {
    displayQuote(listOf(4_000, 3_000, 2_000, 1_000))
}

private fun displayQuote(args: List<Int>) = runBlocking(Default) {
    try {
        args
            .map {
                async { download(it) }
            }
            .forEach { println("await: " + it.await()) }
    } catch (e: Exception) {
        println(e.suppressed)
    }
}

private fun download(arg: Int): String {
    Thread.sleep(arg.toLong())
    throw Exception("Worker Exception $arg")
}
