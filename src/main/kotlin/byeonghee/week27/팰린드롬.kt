package byeonghee.week27

import java.io.*

class 소병희_팰린드롬 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val nums = IntArray(n)
            val dp = Array(n) { BooleanArray(n) }

            readLine().split(" ").forEachIndexed { e, v ->
                nums[e] = v.toInt()

                for(s in e downTo 0) {
                    if (nums[s] != nums[e]) continue

                    if (s + 1 > e - 1) dp[s][e] = true
                    else if (dp[s + 1][e - 1]) dp[s][e] = true
                }
            }

            val sb = StringBuilder()
            var s : Int
            var e : Int
            repeat(readLine().toInt()) {
                readLine().split(" ").let {
                    s = it[0].toInt() - 1
                    e = it[1].toInt() - 1
                }
                sb.appendLine(if (dp[s][e]) 1 else 0)
            }
            print(sb)
        }
    }
}

fun main() {
    소병희_팰린드롬.solve()
}