package byeonghee.`12week`

import java.io.*

class 소병희_안녕 {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))
        lateinit var dp : Array<IntArray>

        fun solve() {
            val n = br.readLine().toInt()
            val loss = listOf(0).plus(br.readLine().split(" ").map { it.toInt() })
            val joy = listOf(0).plus(br.readLine().split(" ").map { it.toInt() })
            dp = Array(n+1) { IntArray(101) }

            for(i in 1..n) for(l in 1 until 100) {
                if (loss[i] <= l) {
                    dp[i][l] = dp[i-1][l].coerceAtLeast(dp[i-1][l - loss[i]] + joy[i])
                }
                else {
                    dp[i][l] = dp[i-1][l]
                }
            }
            println(dp[n][99])
        }
    }
}

fun main() {
    소병희_안녕.solve()
}