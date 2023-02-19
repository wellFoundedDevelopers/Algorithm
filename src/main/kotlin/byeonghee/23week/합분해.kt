package byeonghee.`23week`

import java.io.*

class 소병희_합분해 {

    companion object {
        const val MOD = 1_000_000_000

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, k) = readLine().split(" ").map { it.toInt() }
            val dp = Array(n + 1) { IntArray(k) { 1 } }

            for(r in 1 .. n) for(c in 1 until k) {
                dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % MOD
            }

            println(dp[n][k - 1])
        }
    }
}

fun main() {
    소병희_합분해.solve()
}