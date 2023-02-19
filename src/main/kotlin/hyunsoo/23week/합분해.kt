package hyunsoo.`23week`

/**
 *
 * <문제>
 * [합분해](https://www.acmicpc.net/problem/2225)
 *
 * - 아이디어
 *
 * 0을 만들 수 있는 경우 0
 * - 0
 * 1을 만들 수 있는 경우 2
 * - 1 0
 * 2를 만들 수 있는 경우
 * - 1 1 0
 * - 1 0 1
 * - 0 1 1
 * - 2 0
 * - 0 2
 * - 트러블 슈팅
 *
 */
class `전현수_합분해` {

    fun solution() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val dp = Array(n + 1) {
            IntArray(k + 1) { it }
        }

        for (rowIndex in 1 until n) {
            dp[rowIndex][1] = 1
        }

        for (i in 2..n) {
            for (j in 1..k) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1_000_000_000
            }
        }

        println(dp[n][k])

    }
}

fun main() {
    전현수_합분해().solution()
}