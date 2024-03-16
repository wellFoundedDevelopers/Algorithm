package byeonghee.week54

import java.util.PriorityQueue
import kotlin.math.abs

class 소병희_우체국 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            var total = 0L
            val pq = PriorityQueue<LongArray> { a, b -> (a[0] - b[0]).toInt() }

            repeat(n) { i ->
                val (x, a) = readLine().split(" ").map { it.toLong() }
                pq.add(longArrayOf(x, a))
                total += a
            }

            var people = 0L
            var diff = total
            var answer = 0L

            while(pq.isNotEmpty()) {
                val (x, a) = pq.poll()

                if (abs(total - a - people * 2) < diff) {
                    diff = abs(total - a - people * 2)
                    answer = x
                    people += a
                }
                else break
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_우체국.solve()
}