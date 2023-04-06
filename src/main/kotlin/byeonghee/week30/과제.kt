package byeonghee.week30

import java.io.*
import java.util.PriorityQueue

// 정렬 후 전체 순회가 필요할 땐 pq -> sort

class 소병희_과제 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val hws = Array(n) { readLine().split(" ").let { intArrayOf(it[0].toInt(), it[1].toInt()) } }
            val mins = PriorityQueue<Int>()
            var answer = 0

            hws.sortBy { it[0] }
            for((d, w) in hws) {
                if (d > mins.size) {
                    answer += w
                    mins.add(w)
                }
                else if (mins.peek() <w) {
                    answer -= mins.poll()
                    answer += w
                    mins.add(w)
                }
            }

            print(answer)
        }
    }
}

fun main() {
    소병희_과제.solve()
}