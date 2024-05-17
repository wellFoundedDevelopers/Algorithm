package byeonghee.week62

import java.util.PriorityQueue

class 소병희_최소스패닝트리 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (v, e) = readLine().split(" ").map { it.toInt() }
            val visited = IntArray(v+1) { it }
            val pq = PriorityQueue<IntArray> { a, b -> a[2] - b[2] }
            var answer = 0

            repeat(e) {
                pq.add(readLine().split(" ").map { it.toInt() }.toIntArray())
            }

            while(pq.isNotEmpty()) {
                val (a, b, w) = pq.poll()

                var parA = a
                var parB = b

                while(visited[parA] != parA) {
                    parA = visited[parA]
                }
                while(visited[parB] != parB) {
                    parB = visited[parB]
                }

                if (parA == parB) continue

                if (parA < parB) {
                    visited[parB] = parA
                }
                else {
                    visited[parA] = parB
                }

                answer += w
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_최소스패닝트리.solve()
}