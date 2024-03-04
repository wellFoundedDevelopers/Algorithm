package byeonghee.week52

import java.util.PriorityQueue

class 소병희_N번째큰수 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val table = Array(n) { IntArray(n) }
            val pq = PriorityQueue(n * 2) { a: IntArray, b: IntArray -> b[2] - a[2] }

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    table[i][j] = v.toInt()
                    if (i == n-1)
                        pq.add(intArrayOf(i, j, table[i][j]))
                }
            }

            var cnt = 0
            while(pq.isNotEmpty()) {
                val (r, c, v) = pq.poll()
                cnt++

                if (cnt == n) {
                    println(v)
                    return
                }
                if (r > 0)
                    pq.add(intArrayOf(r - 1, c, table[r - 1][c]))
            }
        }
    }
}

fun main() {
    소병희_N번째큰수.solve()
}