package basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

suspend fun main() {
    measureTimeMillis {
        val jobs = List(4_000_000) {
            GlobalScope.launch {
                delay(1_000)
            }
        }
        jobs.forEach { it.join() }
    }.let { println("Execution took $it ms") }
}
