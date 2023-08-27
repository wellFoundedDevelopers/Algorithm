package hyunsoo.`42week`

/**
 *
 * <문제>
 * [1, 2, 3 더하기 3](https://www.acmicpc.net/problem/15988)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_1_2_3_더하기_3` {

    fun solution() {

        val dp = LongArray(1_000_001)

        dp[1] = 1
        dp[2] = 2
        dp[3] = 4
        dp[4] = 7

        for (i in 5..1_000_000) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1_000_000_009
        }

        val testCnt = readln().toInt()

        repeat(testCnt) {
            println(dp[readln().toInt()] % 1_000_000_009)
        }
    }
}

fun main() {
    전현수_1_2_3_더하기_3().solution()
}