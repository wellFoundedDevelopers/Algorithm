package jimin.`64week`

import java.util.PriorityQueue

class `내리막 길` {
    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val ground = mutableListOf<MutableList<Int>>()

        for (i in 0 until n) {
            ground.add(readln().split(" ").map { it.toInt() } as MutableList<Int>)
        }

        val dx = mutableListOf(0, 0, 1, -1)
        val dy = mutableListOf(1, -1, 0, 0)
        val pq = PriorityQueue<Pair<Int, Int>> { a, b ->
            if (ground[a.first][a.second] > ground[b.first][b.second]) -1 else 1
        }
        pq.add(0 to 0)
        val visited = MutableList(n) { MutableList(m) { 0 } }
        visited[0][0] = 1

        while (pq.isNotEmpty()) {
            val (nx, ny) = pq.poll()

            for (i in 0 until 4) {
                if (nx + dx[i] in 0 until n && ny + dy[i] in 0 until m && ground[nx + dx[i]][ny + dy[i]] < ground[nx][ny]) {
                    if (visited[nx + dx[i]][ny + dy[i]] == 0) {
                        pq.add(nx + dx[i] to ny + dy[i])
                    }
                    visited[nx + dx[i]][ny + dy[i]] += visited[nx][ny]
                }
            }
        }

        println(visited[n - 1][m - 1])
    }
}

fun main() {
    `내리막 길`().solve()
}