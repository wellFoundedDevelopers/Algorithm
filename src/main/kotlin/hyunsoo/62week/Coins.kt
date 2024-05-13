package hyunsoo.`62week`

import kotlin.math.max

/**
 *
 * <문제>
 * [Coins](https://www.acmicpc.net/problem/3067)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_Coins` {

    fun solution() {

        val testCaseCnt = readln().toInt()
        val answerList = mutableListOf<Int>()

        repeat(testCaseCnt) {

            val n = readln().toInt()
            val coins = listOf(0) + readln().split(" ").map { it.toInt() }
            val targetMoney = readln().toInt()

            val dp = Array(n + 1) {
                IntArray(targetMoney + 1)
            }

            dp[0][0] = 1

            // 현재 코인의 인덱스
            for (i in 1..n) {
                // 목표 금액
                for (j in 0..targetMoney) {

                    dp[i][j] = dp[i - 1][j]

                    if (j >= coins[i]) {
                        dp[i][j] += dp[i][j - coins[i]]
                    }
                }

            }

            answerList.add(dp[n][targetMoney])

        }

        answerList.forEach {
            println(it)
        }
    }
}

fun main() {
    전현수_Coins().solution()
}