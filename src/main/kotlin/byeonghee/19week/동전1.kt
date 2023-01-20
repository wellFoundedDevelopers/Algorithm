package byeonghee.`19week`

import java.io.*

class 소병희_동전1 {

    companion object {
        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, k) = readLine().split(" ").map { it.toInt() }
            val coins = IntArray(n) { readLine().toInt() }.sorted()
            val dp = IntArray(k + 1)

            for(coin in coins) {
                if (coin > k) continue
                dp[coin]++

                for(i in 0 .. k - coin) {
                    dp[i + coin] += dp[i]
                }
            }
            println(dp[k])
        }
    }
}

fun main() {
    소병희_동전1.solve()
}