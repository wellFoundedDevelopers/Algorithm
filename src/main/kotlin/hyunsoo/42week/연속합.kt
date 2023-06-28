package hyunsoo.`42week`

import kotlin.math.max

/**
 *
 * <문제>
 * [연속합](https://www.acmicpc.net/problem/1912)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_연속합` {

    fun solution() {
        val n = readln().toInt()
        val sequence = listOf(0) + readln().split(" ").map { it.toInt() }

        val dp = IntArray(n + 1)
        dp[1] = sequence[1]

        for (i in 2..n) {
            dp[i] = max(sequence[i] + dp[i - 1], sequence[i])
        }

        println(dp.drop(1).maxOf { it })
    }
}

fun main() {
    전현수_연속합().solution()
}