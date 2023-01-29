package hyunsoo.`19week`

import kotlin.math.max

/**
 *
 * <문제>
 * [평범한 배낭](https://www.acmicpc.net/problem/12865)
 *
 * - 아이디어
 * 전형적인 냅색..!
 *
 * - 트러블 슈팅
 *
 */

data class Stuff(val weight: Int = 0, val value: Int = 0)


class `전현수_평범한_배낭` {

    fun solution() {

        val (stuffCnt, maxWeight) = readln().split(" ").map { it.toInt() }
        val dp = Array(stuffCnt + 1) {
            IntArray(maxWeight + 1)
        }

        val stuffs = Array(stuffCnt + 1) {
            Stuff()
        }

        repeat(stuffCnt) { index ->
            readln().split(" ").map { it.toInt() }.apply {
                stuffs[index + 1] = Stuff(this.first(), this.last())
            }
        }

        for (i in 1..stuffCnt) {
            for (j in 1..maxWeight) {

                val (stuffWeight, stuffValue) = stuffs[i]

                // 위에서 그냥 내려올 경우(현재 물건을 넣지 않음.
                // VS 대각선에서 내려올 경우(현재 물건을 넣어줌.)
                if (0 <= j - stuffWeight) {
                    dp[i][j] =
                        max(dp[i - 1][j], dp[i - 1][j - stuffWeight] + stuffValue)
                } else {
                    dp[i][j] = dp[i - 1][j]
                }

            }

        }

        println(dp[stuffCnt][maxWeight])

    }
}

fun main() {
    전현수_평범한_배낭().solution()
}