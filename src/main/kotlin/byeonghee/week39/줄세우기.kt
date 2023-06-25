package byeonghee.week39

import java.io.*

// 참고: https://chanhuiseok.github.io/posts/algo-49/

class 소병희_줄세우기 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {

            val n = readLine().toInt()
            val line = IntArray(n)
            val dp = IntArray(n)
            var lis = 0

            for(i in 0 until n) {
                line[i] = readLine().toInt()
                dp[i] = 1
                for(j in 0 until i) {
                    if (line[j] < line[i]) dp[i] = maxOf(dp[j] + 1, dp[i])
                }
                lis = maxOf(lis, dp[i])
            }

            println(n - lis)
        }
    }
}

fun main() {
    소병희_줄세우기.solve()
}