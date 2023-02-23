package basics

import kotlinx.coroutines.*
import java.lang.Thread.currentThread
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        val jobs = List(4_000) {
            thread {
                Thread.sleep(1_000)
            }
        }
        jobs.forEach { it.join() }
    }.let { println("Execution took $it ms") }
}
