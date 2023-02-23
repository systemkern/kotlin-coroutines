package continuations

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    displayQuote(listOf(4_000, 3_000, 2_000, -1))
}

private fun displayQuote(args: List<Long>) = runBlocking(Default) {
    args.map { it: Long ->
            async { download(it) }
        }
        .forEach { it: Deferred<String> ->
            println(it.await())
        }
}

private fun download(arg: Long): String {
    if (arg <= 0) throw Exception("Foo")
    /* do long-running work */
    Thread.sleep(arg)
    println("$arg: Thread ${Thread.currentThread().name}")
    return "THE QUOTE $arg"
}
