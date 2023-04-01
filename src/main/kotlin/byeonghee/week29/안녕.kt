package byeonghee.week29

import java.io.*

class 소병희_안녕 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val cost = IntArray(n)
            val joy = IntArray(n)
            val dp = IntArray(100)

            readLine().split(" ").forEachIndexed { i, v -> cost[i] = v.toInt() }
            readLine().split(" ").forEachIndexed { i, v -> joy[i] = v.toInt() }

            for(f in 0 until n) {
                for(c in 99 downTo cost[f]) {
                    dp[c] = maxOf(dp[c], dp[c - cost[f]] + joy[f])
                }
            }

            print(dp[99])
        }
    }
}

fun main() {
    소병희_안녕.solve()
}