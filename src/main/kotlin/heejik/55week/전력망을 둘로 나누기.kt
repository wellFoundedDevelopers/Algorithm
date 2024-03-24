package heejik.`55week`

import kotlin.math.abs
import kotlin.math.min

class `전력망을 둘로 나누기` {
    var answer: Int = Int.MAX_VALUE
    var n = 0
    lateinit var relations: MutableList<BooleanArray>
    lateinit var visited: BooleanArray

    fun solution(_n: Int, wires: Array<IntArray>): Int {
        n = _n
        relations = MutableList(size = n) { BooleanArray(size = n) }
        visited = BooleanArray(size = n)

        wires.forEach { wire ->
            val (x, y) = wire.map { it - 1 }

            relations[x][y] = true
            relations[y][x] = true
        }


        for ((x, y) in wires) {
            relations[x - 1][y - 1] = false
            relations[y - 1][x - 1] = false
            val count = bfs()
            relations[x - 1][y - 1] = true
            relations[y - 1][x - 1] = true
            answer = min(answer, abs(n - count * 2))
            visited.fill(false)
        }

        return answer
    }


    private fun bfs(): Int {
        val queue = ArrayDeque<Int>()
        queue.add(0)
        visited[0] = true
        var count = 1

        while (queue.isNotEmpty()) {
            val num = queue.removeFirst()
            relations[num].forEachIndexed { idx, bol ->
                if (visited[idx].not() && bol) {
                    queue.add(idx)
                    visited[idx] = true
                    count++
                }
            }
        }
        return count
    }
}

fun main() {
    val distances = MutableList<Int>(size = 20001) { -1 }
    println(distances.max())
}