package hyunsoo.`49week`

import kotlin.math.max

/**
 *
 * <문제>
 * [벼락치기](https://www.acmicpc.net/problem/14728)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_벼락치기` {

    private data class Question(val expectedTime: Int, val score: Int)

    fun solution() {

        val (unitCnt, totalTime) = readln().split(" ").map { it.toInt() }

        val questionList = mutableListOf<Question>().apply {
            add(Question(0, 0))
        }

        repeat(unitCnt) {

            val (expectedTime, score) = readln().split(" ").map { it.toInt() }
            questionList.add(Question(expectedTime, score))

        }

        val dp = Array(unitCnt + 1) {
            IntArray(totalTime + 1)
        }
        for (i in 1..unitCnt) {
            val curQuestion = questionList[i]
            for (j in 0..totalTime) {
                if (curQuestion.expectedTime <= j) {
                    dp[i][j] = max(
                        dp[i - 1][j],
                        dp[i - 1][j - curQuestion.expectedTime] + curQuestion.score
                    )
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }

        println(dp[unitCnt][totalTime])


    }
}

fun main() {
    전현수_벼락치기().solution()
}