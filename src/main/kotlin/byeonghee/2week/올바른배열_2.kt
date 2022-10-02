package byeonghee.`2week`

import java.io.BufferedReader
import java.io.InputStreamReader

var num = 0
val rangeDupCount = hashMapOf<Int, Int>()

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().trim().toInt()

    repeat(size) {
        num = readLine().trim().toInt()
        (num until num + 5).forEach {
            rangeDupCount[it] = rangeDupCount.getOrDefault(it, 0) + 1
        }
    }

    println(5 - rangeDupCount.maxOf { it.value })
}