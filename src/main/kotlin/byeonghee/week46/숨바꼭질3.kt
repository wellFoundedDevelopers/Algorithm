package byeonghee.week46

class 소병희_숨바꼭질3 {

    companion object {
        const val INF = 999_999

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, k) = readLine().split(" ").map { it.toInt() }

            if (n >= k) {
                println(n - k)
                return@with
            }

            val dp = IntArray(100_001) { it - n }
            repeat(n) { i -> dp[i] = n - i }

            for(i in n .. maxOf(k, n * 2).coerceAtMost(100_000)) {
                if (i > 0) dp[i] = minOf(dp[i], dp[i-1] + 1)
                if (i < 100_000) dp[i] = minOf(dp[i], dp[i+1] + 1)
                if (i%2 == 0) dp[i] = minOf(dp[i], dp[i/2])

                if (i <= 50_000) dp[i*2] = minOf(dp[i*2], dp[i])
                if (i > 0) dp[i-1] = minOf(dp[i-1], dp[i] + 1)
                if (i < 100_000) dp[i+1] = minOf(dp[i+1], dp[i] + 1)
            }

            println(dp[k])
        }
    }
}

fun main() {
    소병희_숨바꼭질3.solve()
}