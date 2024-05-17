package byeonghee.week62

class 소병희_Coins {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val t = readLine().toInt()
            val sb = StringBuilder()

            repeat(t) {
                val n = readLine().toInt()
                val coins = IntArray(n)

                readLine().split(" ").forEachIndexed { i, v -> coins[i] = v.toInt() }

                val m = readLine().toInt()
                val dp = IntArray(m+1)

                dp[0] = 1
                for(coin in coins) {
                    for(price in coin .. m) {
                        dp[price] += dp[price - coin]
                    }
                }

                sb.appendLine(dp[m])
            }

            println(sb)
        }
    }
}

fun main() {
    소병희_Coins.solve()
}