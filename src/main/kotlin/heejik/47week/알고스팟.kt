package heejik.`47week`

import kotlin.properties.Delegates

class 알고스팟 {

    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    var m by Delegates.notNull<Int>()
    var n by Delegates.notNull<Int>()
    private val maze = mutableListOf<List<Int>>()
    lateinit var visited: List<MutableList<Boolean>>

    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        visited = MutableList(m) { MutableList(n) { false } }

        repeat(m) {
            maze.add(readln().toList().map { it.digitToInt() })
        }

        findAnswer().run { println(this) }
    }

    private fun findAnswer(): Int {
        val dequeue = ArrayDeque<Pair<Pos, Int>>()
        dequeue.add(Pos(0, 0) to 0)

        while (dequeue.isNotEmpty()) {
            val (pos, count) = dequeue.removeFirst()

            if (pos.x == m - 1 && pos.y == n - 1) {
                return count
            }

            for (i in 0 until 4) {
                val nx = pos.x + dx[i]
                val ny = pos.y + dy[i]
                if (nx in 0 until m && ny in 0 until n) {
                    if (visited[nx][ny].not()) {
                        if (maze[nx][ny] == 0) dequeue.addFirst(Pos(nx, ny) to count)
                        if (maze[nx][ny] == 1) dequeue.addLast(Pos(nx, ny) to count + 1)
                        visited[nx][ny] = true
                    }
                }
            }
        }

        return Int.MAX_VALUE
    }
}

fun main() {
    알고스팟().solve()
}