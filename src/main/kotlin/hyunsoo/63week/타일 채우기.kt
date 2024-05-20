package hyunsoo.`63week`

/**
 *
 * <문제>
 * [타일 채우기](https://www.acmicpc.net/problem/2133)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_타일_채우기` {

    fun solution() {
        val dp = IntArray(31)
        val n = readln().toInt()

        dp[0] = 1
        dp[2] = 3

        for (i in 4 .. 30) {
            dp[i] = dp[i - 2] * 3
            for (j in 4 .. i step 2)
                dp[i] += dp[i - j] * 2
        }

        println(dp[n])
    }
}

fun main() {
    전현수_타일_채우기().solution()
}