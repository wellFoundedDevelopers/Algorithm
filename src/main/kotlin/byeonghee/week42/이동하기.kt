package byeonghee.week42

import java.io.BufferedReader
import java.io.InputStreamReader

class 소병희_이동하기 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val maze = Array(n) { IntArray(m) }
            val dp = Array(n) { IntArray(m) }

            for(i in 0 until n) {
                readLine().split(" ").forEachIndexed { j, v ->
                    maze[i][j] = v.toInt()
                }
            }

            dp[0][0] = maze[0][0]
            for(i in 0 until n) for (j in 0 until m) {
                if (i > 0) {
                    dp[i][j] = dp[i-1][j] + maze[i][j]
                }
                if (j > 0) {
                    dp[i][j] = maxOf(dp[i][j], dp[i][j-1] + maze[i][j])
                }
            }

            println(dp[n-1][m-1])
        }
    }
}

fun main() {
    소병희_이동하기.solve()
}