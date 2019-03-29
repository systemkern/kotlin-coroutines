package other

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        runBlocking(Dispatchers.Default) {
            launch {
                val sum = (1..100_000)
                    .map { async(Dispatchers.Default) { complicatedComputation(it) } }
                    .map { it.await() }
                    .sum()
                println("Sum is $sum on Thread ${Thread.currentThread().name}")
            }.join()
        }
    }.also {
        println("Printing Info on Thread ${Thread.currentThread().name}")
        println("Execution took $it ms")
    }
}


private suspend fun complicatedComputation(i: Int): Int {
    println("Computing $i on Thread ${Thread.currentThread().name}")
    delay(1_000)
    return 1
}
