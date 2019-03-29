import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println("1 from ${Thread.currentThread().name}")
        runBlocking {
            val job = launch(Dispatchers.Unconfined) {
                printComplicated("2")
            }
            println("3 from ${Thread.currentThread().name}")
        }
    }.let {
        println("Execution took $it ms")
    }
}

private suspend fun printComplicated(arg: String) {
    println("$arg from ${Thread.currentThread().name}")
}
