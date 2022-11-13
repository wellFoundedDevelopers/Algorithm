package byeonghee.`9week`

import java.io.*

class 소병희_123더하기4 {
    companion object {
        const val LAST_ONE = 0
        const val LAST_TWO = 1
        const val LAST_THREE = 2

        val br = BufferedReader(InputStreamReader(System.`in`))
        val dp = Array(10004) { intArrayOf(1, 0, 0) }
        var tc = 0

        fun solve() {
            val t = br.readLine().toInt()

            repeat(t) {
                tc = br.readLine().toInt()
                println(findCases(tc + 3, LAST_THREE))
            }
        }

        fun findCases(n: Int, e: Int): Int {
            if (n <= e) return 0
            if (n == 1) return if (e == LAST_ONE)  1 else 0
            if (n == 2) return if (e <= LAST_TWO) 1 else 0
            if (n == 3) return if (e <= LAST_THREE) 1 else 0

            if (dp[n][e] > 0) return dp[n][e]

            var ans = 0
            for(i in 0..e) {
                ans += findCases(n - e - 1, i)
            }
            dp[n][e] = ans
            return ans
        }
    }
}

fun main() {
    소병희_123더하기4.solve()
}