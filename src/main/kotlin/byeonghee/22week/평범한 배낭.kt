package byeonghee.`22week`

import java.io.*

class 소병희_평범한배낭 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, k) = readLine().split(" ").map { it.toInt() }
            val dp = IntArray(k + 1)

            var w : Int
            var v : Int
            for(i in 0 until n) {
                readLine().split(" ").let {
                    w = it[0].toInt()
                    v = it[1].toInt()
                }

                for(cur in k downTo 1) {
                    if (w <= cur) {
                        dp[cur] = maxOf(dp[cur], dp[cur - w] + v)
                    }
                    dp[cur]
                }
            }
            println(dp[k])
        }
    }
}

fun main() {
    소병희_평범한배낭.solve()
}