package other

fun main() {
    for (value in seq) {
        println("for: value is $value")
    }
    println()
    for (value in loopSequence) {
        println("for: value is $value")
    }
}

private val seq = sequence {
    println("squence: one")
    yield(1)

    println("squence: two")
    yield(2)

    println("squence: three")
    yield(3)

    println("squence: four")
    yield(4)
}

private val loopSequence = sequence {
    repeat(10) {
        println("loop squence: $it in ${Thread.currentThread().name}")
        Thread.sleep(1_000)
        yield(it)
    }
}
