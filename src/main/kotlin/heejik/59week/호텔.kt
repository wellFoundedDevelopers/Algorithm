package heejik.`59week`

import kotlin.math.max

class 호텔 {

    fun solve() {
        val (c, n) = readln().split(' ').map { it.toInt() }
        val dp = List(n) { MutableList(size = 100001) { 0 } }
        val prices = mutableListOf<Int>()
        val customers = mutableListOf<Int>()
        repeat(n) { idx ->
            val (price, customer) = readln().split(' ').map { it.toInt() }
            dp[idx][price] = customer
            prices.add(price)
            customers.add(customer)
        }
        for (i in 0 until n) {
            for (j in 1 until 100001) {
                if (i == 0) {
                    if (j - prices[i] >= 0) {
                        dp[i][j] = dp[i][j - prices[i]] + customers[i]
                    }
                } else {
                    if (j - prices[i] >= 0) {
                        dp[i][j] = max(dp[i - 1][j], dp[i][j - prices[i]] + customers[i])
                    } else {
                        dp[i][j] = max(dp[i - 1][j], dp[i][j])
                    }
                }
            }
        }

        print(dp[n - 1].indexOfFirst { it >= c })
    }
}

fun main() {
    호텔().solve()
}