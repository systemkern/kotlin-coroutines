package basics

import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println("1")
        printlnComplicated("2")
        println("3")
    }.also { println("Execution took $it ms") }
}

private fun printlnComplicated(arg: String) {
    Thread.sleep(1_000)
    println(arg)
}
