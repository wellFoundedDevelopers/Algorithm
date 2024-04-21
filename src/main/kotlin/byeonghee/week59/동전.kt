package byeonghee.week59

class 소병희_동전 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val t = readLine().toInt()
            repeat(t) {
                val n = readLine().toInt()
                val coins = IntArray(n)

                readLine().split(" ").forEachIndexed { i, v -> coins[i] = v.toInt() }

                val price = readLine().toInt()
                val dp = Array(price + 1) { IntArray(n) }

                repeat(n) { i -> if (price >= coins[i]) dp[coins[i]][i] = 1 }
                for(p in 1 .. price) {
                    for((i, c) in coins.withIndex()) {
                        if (p < c) continue
                        for(pre in 0 .. i) {
                            dp[p][i] += dp[p - c][pre]
                        }
                    }
                }

                println(dp[price].sumOf { it })
            }
        }
    }
}

fun main() {
    소병희_동전.solve()
}
