package hyunsoo.`19week`

/**
 *
 * <문제>
 * [2xn 타일링](https://www.acmicpc.net/problem/11726)
 *
 * - 아이디어
 *
 * // 1 -> 1
 * // 2 -> 2
 * // 3 -> 3
 * // 4 -> 5
 * // 5 -> 7
 *
 * n-1 + n-2?
 *
 * - 트러블 슈팅
 * 1 일때도 dp[2]를 초기화해서 인덱스 초과 에러 발생
 * 나머지 연산을 dp값을 계산한 후에 해서 틀린 듯?
 *
 */
class `전현수_2xn_타일링` {

    fun solution() {

        val n = readln().toInt()

        if (n == 1) {
            println("1")
        } else if (n == 2) {
            println("2")
        } else {
            val dp = IntArray(n + 1) { 0 }
            dp[1] = 1
            dp[2] = 2

            for (i in 3..n) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 10007
            }

            println(dp[n])
        }

    }
}

fun main() {
    전현수_2xn_타일링().solution()
}