package byeonghee.week44

const val MOD = 10_007

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, h) = readLine().split(" ").map { it.toInt() }
    val dp = Array(n+1) { IntArray(h+1) }

    dp[0][0] = 1
    repeat(n) { i ->

        for(k in 0 .. h) {
            dp[i+1][k] = dp[i][k]
        }

        readLine().split(" ").forEach {
            val j = it.toInt()
            for(k in 0 .. h - j) {
                dp[i+1][k + j] = (dp[i+1][k + j] + dp[i][k]) % MOD
            }
        }
    }

    println(dp[n][h])
}