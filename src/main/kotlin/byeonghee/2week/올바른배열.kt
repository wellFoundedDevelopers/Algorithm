package byeonghee.`2week`

import java.io.BufferedReader
import java.io.InputStreamReader

var answer = 5

fun min(a: Int, b: Int) = if (a < b) a else b

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().trim().toInt()
    val arr = mutableListOf<Int>()

    repeat(size) {
        arr.add(readLine().trim().toInt())
    }

    arr.forEach { num ->
        val range = num+1 until num+5
        var numToAdd = 4

        range.forEach {
            if (arr.contains(it)) {
                numToAdd--
            }
        }
        answer = min(answer, numToAdd)
    }

    println(answer)
}