package hyunsoo.`22week`

import kotlin.math.max

/**
 *
 * <문제>
 * [평범한 배낭](https://www.acmicpc.net/problem/12865)
 *
 * - 아이디어
 *
 * 냅색.. 근본...
 *
 * - 트러블 슈팅
 *
 * 같은 물건이 2개 들어가게 코드를 작성하였음.
 * -> row를 stuff의 번호로 두고 해당 stuff를 무조건 넣는 방식으로 구현했음.
 * - 최댓값을 갱신할 때 해당 row를 기준으로 탐색하니까 중복된 물건을 넣을 수가 있게 되었음.
 *
 * 현재 물건의 무게가 너무 높아 넣을 수 없을경우 최댓값(윗칸)을 가져오게 해야하는데,
 * 그냥 넘어가버렸음.
 */
class `전현수_평범한_배낭` {

    private data class StuffInfo(val weight: Int = 0, val value: Int = 0)

    fun solution() {

        val (stuffCnt, maxWeight) = readln().split(" ").map { it.toInt() }
        val dp = Array(stuffCnt + 1) {
            IntArray(maxWeight + 1)
        }

        val stuffs = Array(stuffCnt + 1) {
            StuffInfo()
        }

        repeat(stuffCnt) { index ->
            val (weight, value) = readln().split(" ").map { it.toInt() }
            stuffs[index + 1] = StuffInfo(weight, value)
        }

        for (stuffIndex in 1..stuffCnt) {
            for (curWeight in 1..maxWeight) {

                val (stuffWeight, stuffValue) = stuffs[stuffIndex]

                if (curWeight < stuffWeight) {
                    dp[stuffIndex][curWeight] = dp[stuffIndex - 1][curWeight]
                    continue
                }

                dp[stuffIndex][curWeight] = max(
                    dp[stuffIndex - 1][curWeight],
                    stuffValue + dp[stuffIndex - 1][curWeight - stuffWeight]
                )
            }

        }

        println(dp[stuffCnt][maxWeight])
    }
}

fun main() {
    전현수_평범한_배낭().solution()
}