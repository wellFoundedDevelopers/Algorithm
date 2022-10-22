package heejik.`5week`

import java.io.BufferedReader
import java.io.InputStreamReader


val lines = mutableListOf<Long>()

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    var answer = 0L
    val (k, n) = readLine().split(' ').map { it.toInt() }
    var s = 0L

    repeat(k) {
        val line = readLine().toLong()
        lines.add(line)
        s += line
    }

    var start = 1L
    var end = s / n
    while (start <= end) {
        val mid = (end + start) / 2
        if (lines.sumOf { it/mid } >= n){
            answer = mid
            start = mid + 1
        } else {
            end = mid - 1
        }
    }

    println(answer)
}
