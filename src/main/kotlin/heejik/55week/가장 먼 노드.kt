package heejik.`55week`

import kotlin.math.max

class `가장 먼 노드` {
    val distances = MutableList<Int>(size = 20001) { -1 }.apply { this[1] = 0 }
    val relations = MutableList(20001) { BooleanArray(20001) }
    var maxDistance = 0
    fun solution(n: Int, edge: Array<IntArray>): Int {
        edge.forEach {
            val (x, y) = it
            relations[x][y] = true
            relations[y][x] = true
        }

        return with(bfs()) { distances.count { it == maxDistance } }
    }

    private fun bfs() {
        val queue = ArrayDeque<Int>().apply { add(1) }

        while (queue.isNotEmpty()) {
            val now = queue.removeFirst()
            relations[now].forEachIndexed { idx, b ->
                if (b && distances[idx] == -1) {
                    distances[idx] = distances[now] + 1
                    queue.add(idx)
                    maxDistance = max(maxDistance, distances[idx])
                }
            }
        }
    }
}