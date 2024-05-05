package byeonghee.week61

import java.util.PriorityQueue

class 소병희_파일합치기 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val T = readLine().toInt()
            val sb = StringBuilder()
            val pq = PriorityQueue<Long>()

            repeat(T) {
                val K = readLine().toInt()
                var answer = 0L

                readLine().split(" ").forEachIndexed { i, v ->
                    pq.add(v.toLong())
                }

                while(pq.size > 1) {
                    val one = pq.poll()
                    val two = pq.poll()
                    answer += one + two
                    pq.add(one + two)
                }

                pq.clear()
                sb.appendLine(answer)
            }

            println(sb)
        }
    }
}

fun main() {
    소병희_파일합치기.solve()
}