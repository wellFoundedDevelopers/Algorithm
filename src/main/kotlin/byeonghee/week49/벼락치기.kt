package byeonghee.week49

class 소병희_벼락치기 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, T) = readLine().split(" ").map { it.toInt() }
            val ch = Array(n) {
                readLine().split(" ").let {
                    it[0].toInt() to it[1].toInt()
                }
            }

            val dp = IntArray(T + 1)

            for(i in 0 until n) {
                for(j in ch[i].first .. T) {
                    dp[j] = maxOf(dp[j-1], dp[j-ch[i].first] + ch[i].second)
                }
            }

            println(dp[T])
        }
    }
}

fun main() {
    소병희_벼락치기.solve()
}