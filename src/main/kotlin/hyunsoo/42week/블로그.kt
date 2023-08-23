package hyunsoo.`42week`

import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [블로그](https://www.acmicpc.net/problem/21921)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_블로그` {

    fun solution() {

        val (n, x) = readln().split(" ").map { it.toInt() }

        val visitors = readln().split(" ").map { it.toInt() }
            .apply {
                if (this.sum() == 0) {
                    println("SAD")
                    exitProcess(0)
                }
            }

        var max = visitors.subList(0, x).sum()
        var maxCnt = 1
        var curSum = max

        for (startIndex in 0 until n - x) {

            val endIndex = startIndex + x

            val newSum = curSum - visitors[startIndex] + visitors[endIndex]

            if (newSum == max) {
                maxCnt++
            } else if (max < newSum) {
                maxCnt = 1
                max = newSum
            }

            curSum = newSum
        }

        println(max)
        println(maxCnt)
    }
}

fun main() {
    전현수_블로그().solution()
}