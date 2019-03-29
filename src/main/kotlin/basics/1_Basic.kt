package basics

import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println("1")
        println("2")
        println("3")
    }.also { println("Execution took $it ms") }
}
