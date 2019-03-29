package basics

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.currentThread
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println("1 from ${currentThread().name}")
        runBlocking(Dispatchers.Default) {
            launch {
                printlnComplicated(arg = "2")
            }
            println("3 from ${currentThread().name}")
            Thread.sleep(1_000)
        }
    }.also { println("Execution took $it ms") }
}

private suspend fun printlnComplicated(arg: String) {
    Thread.sleep(1_000)
    println("$arg from ${currentThread().name}")
}
