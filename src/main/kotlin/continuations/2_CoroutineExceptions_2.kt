package continuations

import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.management.loading.ClassLoaderRepository
import kotlin.system.exitProcess

/**
 * This example will show how multiple exceptions are propagated properly through a coroutine scope.
 * Every Invocation of the download function throws an exception, and all those exceptions are
 * propagated to the calling function.
 * The first one is the actual exception that will be thrown,
 * all others are attached as "suppressed exceptions"
 */
fun main() {
    displayQuote(listOf(4_000, 3_000, 2_000, 1_000))
}

private fun displayQuote(args: List<Int>) {
    try {
        runBlocking(Default) {
            args.map { async { download(it) } }
                .forEach { println("await: " + it.await()) }
        }
    } catch (e: Throwable) {
        println(e)
        e.suppressed.forEach { println(it) }
        println()
        throw e
    }
}

private fun download(arg: Int): String {
    Thread.sleep(arg.toLong())
    throw Exception("Worker Exception $arg")
}
