package byeonghee.week59

class 소병희_호텔 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (c, n) = readLine().split(" ").map { it.toInt() }
            val cities = Array(n) { IntArray(2) }
            val dp = IntArray(c * 100 + 1)

            repeat(n) { i ->
                val (cost, people) = readLine().split(" ").map { it.toInt() }
                cities[i][0] = cost
                cities[i][1] = people
            }

            for((cost, people) in cities) {
                for(price in 1 .. c * 100) {
                    if (price - cost >= 0) {
                        dp[price] = dp[price]
                                .coerceAtLeast(dp[price - cost] + people)
                    }
                }
            }

            for(i in 1 .. c * 100) {
                if (dp[i] >= c) {
                    println(i)
                    return@with
                }
            }
        }
    }
}

fun main() {
    소병희_호텔.solve()
}