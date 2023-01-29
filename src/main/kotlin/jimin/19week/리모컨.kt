package jimin.`19week`

fun main() {
    val channel = readln().toInt()
    val n = readln().toInt()
    if (n == 0) {
        println(channel.toString().length)
        return
    }
    val wrongButton = readln().split(" ").map { it.toInt() }

    if (channel == 100) {
        println(0)
        return
    }
    var num = 1
    while (true) {
        if (channel - num >= 0 && (channel - num).toString().chunked(1).none { it.toInt() in wrongButton }) {
            println(channel.toString().length + num)
            break
        }
        if ((channel + num).toString().chunked(1).none { it.toInt() in wrongButton }) {
            println(channel.toString().length + num)
            break
        }
        num += 1
    }

}