package hyunsoo.`29week`

import kotlin.math.max

/**
 *
 * <문제>
 * [안녕](https://www.acmicpc.net/problem/1535)
 *
 * - 아이디어
 *
 * 사람의 수는 최대 20 정렬 후 그리디로 ㄱㄱ
 * - 체력을 조금 떨어뜨리는 순서 후, 기쁨을 많이 얻는 순서
 * - 기쁨을 많이 얻는 순서 후 체력을 조금 떨어뜨리는 순서
 *
 * 아... 유형을 보니 냅색 문제네요...
 * - 사람 수가 적고 문제 난이도가 실버2라서 단순 그리디라고 생각했어요..
 *
 *
 * - 트러블 슈팅
 *
 */
class `전현수_안녕` {

    fun solution() {

        val peopleCnt = readln().toInt()
        val loseHealth = listOf(0) + readln().split(" ").map { it.toInt() }
        val gainPleasure = listOf(0) + readln().split(" ").map { it.toInt() }
        val dp = Array(peopleCnt + 1) {
            IntArray(101)
        }

        // i는 사람의 인덱스, j는 스테미나 인덱스
        for (i in 1..peopleCnt) {
            for (j in 1 until 100) {
                // 이 사람과 만나는 경우, 체력이 여유롭다면
                if (loseHealth[i] <= j) {
                    dp[i][j] = max(
                        dp[i - 1][j],
                        dp[i - 1][j - loseHealth[i]] + gainPleasure[i]
                    )
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }

        println(dp[peopleCnt][99])

    }
}

fun main() {
    전현수_안녕().solution()
}
