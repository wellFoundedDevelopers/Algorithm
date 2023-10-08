package byeonghee.week47

import java.util.*

class 소병희_알고스팟 {

    companion object {
        const val INF = 99_999
        const val WALL = 1
        const val ROOM = 0

        val dr = intArrayOf(0, 1, 0, -1)
        val dc = intArrayOf(1, 0, -1, 0)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val maze = Array(m) { IntArray(n) }
            val dist = Array(m) { IntArray(n) { INF } }
            val pq = PriorityQueue<IntArray> { a, b -> a[0] - b[0] }

            repeat(m) { i ->
                readLine().forEachIndexed { j, v ->
                    maze[i][j] = v.digitToInt()
                }
            }

            pq.add(intArrayOf(0, 0, 0))
            while(pq.isNotEmpty()) {
                val (d, r, c) = pq.poll()
                if (dist[r][c] <= d) continue
                dist[r][c] = d

                for(dir in 0 until 4) {
                    val nr = r + dr[dir]
                    val nc = c + dc[dir]
                    if (nr !in maze.indices || nc !in maze[0].indices) continue
                    if (maze[nr][nc] == ROOM) {
                        pq.add(intArrayOf(d, nr, nc))
                    }
                    else if (maze[nr][nc] == WALL) {
                        pq.add(intArrayOf(d+1, nr, nc))
                    }
                }
            }

            println(dist[m-1][n-1])
        }
    }
}

fun main() {
    소병희_알고스팟.solve()
}