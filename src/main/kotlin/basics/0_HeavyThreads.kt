package basics

import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        val jobs = List(6_000) {
            thread {
                Thread.sleep(1_000)
            }
        }
        jobs.forEach { it.join() }
    }.let { println("Execution took $it ms") }
}
