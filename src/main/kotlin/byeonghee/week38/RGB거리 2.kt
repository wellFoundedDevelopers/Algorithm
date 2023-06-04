package byeonghee.week38

import java.io.StreamTokenizer

class 소병희_RGB거리2 {

    companion object {
        const val R = 0
        const val G = 1
        const val B = 2

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val cost = Array(n) { IntArray(3) { input() } }
            val dp = Array(n) { IntArray(3) }
            var answer = Int.MAX_VALUE

            for(start in R .. B) {
                dp[0].fill(2_000_000)
                dp[0][start] = cost[0][start]

                for(i in 1 until n) {
                    dp[i][R] = dp[i-1][G].coerceAtMost(dp[i-1][B]) + cost[i][R]
                    dp[i][G] = dp[i-1][B].coerceAtMost(dp[i-1][R]) + cost[i][G]
                    dp[i][B] = dp[i-1][R].coerceAtMost(dp[i-1][G]) + cost[i][B]
                }

                answer = minOf(answer, dp[n-1][(start+2)%3].coerceAtMost(dp[n-1][(start+1)%3]))
            }

            print(answer)
        }
    }
}

fun main() {
    소병희_RGB거리2.solve()
}