package hyunsoo.`38week`

import kotlin.math.min

/**
 *
 * <문제>
 * [RGB거리 2](https://www.acmicpc.net/problem/17404)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_RGB거리_2` {

    fun solution() {

        val n = readln().toInt()

        val houseInfo = Array(n) {
            IntArray(3)
        }

        repeat(n) { index ->
            readln()
                .split(" ")
                .map { it.toInt() }
                .forEachIndexed { colorIndex, value ->
                    houseInfo[index][colorIndex] = value
                }
        }

        var min = Int.MAX_VALUE

        for (i in 0 until 3) {

            val dp = Array(n) { IntArray(3) { 10000000 } }
            dp[0][i] = houseInfo[0][i]

            for (j in 1 until n) {
                dp[j][0] = houseInfo[j][0] + min(dp[j - 1][1], dp[j - 1][2])
                dp[j][1] = houseInfo[j][1] + min(dp[j - 1][0], dp[j - 1][2])
                dp[j][2] = houseInfo[j][2] + min(dp[j - 1][1], dp[j - 1][0])
            }

            val curMin = dp[n - 1]
                .filterIndexed { index, value ->
                    index != i
                }.minOf { it }
            if (curMin < min) min = curMin

        }

        println(min)

    }

}

fun main() {
    전현수_RGB거리_2().solution()
}