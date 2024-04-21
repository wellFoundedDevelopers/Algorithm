package heejik.`59week`

class 동전 {

    fun solve() {
        val t = readln().toInt()
        repeat(t) {
            val n = readln().toInt()
            val coins = readln().split(' ').map { it.toInt() }
            val price = readln().toInt()
            val dp = MutableList(n + 1) { MutableList(10001) { 0 } }

            for (i in 0 until n) {
                dp[i][0] = 1
            }

            for (i in 0 until n) {
                for (j in 1..price) {
                    if (i == 0) {
                        if (j - coins[0] >= 0) {
                            dp[0][j] = dp[0][j - coins[0]]
                        }
                    } else {
                        if (j - coins[i] >= 0) {
                            dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]]
                        } else {
                            dp[i][j] = dp[i - 1][j]
                        }
                    }
                }
            }
            println(dp[n-1][price])
        }
    }
}


fun main() {
    동전().solve()
}