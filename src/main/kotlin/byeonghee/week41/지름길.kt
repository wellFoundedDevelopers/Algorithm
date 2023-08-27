package byeonghee.week41

import java.io.StreamTokenizer
import java.util.PriorityQueue

class 소병희_지름길 {

    companion object {
        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val d = input()
            val pq = PriorityQueue<IntArray> { a, b -> a[1] - b[1] }
            val dp = IntArray(d + 1) { it }

            repeat(n) { pq.add(intArrayOf(input(), input(), input()))}

            while(pq.isNotEmpty()) {
                val (s, e, c) = pq.poll()

                if (e > d) continue
                if (e - s <= c) continue

                for(j in e .. d) {
                    if (dp[j] < dp[s] + c + j - e) continue
                    dp[j] = minOf(dp[j], dp[s] + c + j - e)
                }
            }

            println(dp[d])
        }
    }
}

fun main() {
    소병희_지름길.solve()
}
