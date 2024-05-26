package byeonghee.week64

class `소병희_가장 긴 증가하는 부분 수열 4` {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val arr = IntArray(n)
            val dp = Array(n) { mutableListOf<Int>() }
            var answer = 0

            readLine().split(" ").forEachIndexed { i, v -> arr[i] = v.toInt() }

            dp[0].add(arr[0])
            for(i in 1 until n) {
                var idx = i
                for(j in i - 1 downTo 0) {
                    if (dp[j].last() < arr[i] && dp[j].size > dp[idx].size) {
                        idx = j
                    }
                }
                if (idx != i) dp[i].addAll(dp[idx])
                dp[i].add(arr[i])

                if (dp[i].size > dp[answer].size) answer = i
            }

            println(dp[answer].size)
            println(dp[answer].joinToString(" "))
        }
    }
}

fun main() {
    `소병희_가장 긴 증가하는 부분 수열 4`.solve()
}