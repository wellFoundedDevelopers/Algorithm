package heejik.`43week`

import kotlin.properties.Delegates

class `쉬운 최단거리` {

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    data class Pos(
        val x: Int,
        val y: Int
    )

    lateinit var target: Pos
    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    val board = mutableListOf<MutableList<Int>>()

    fun solve() = with(System.`in`.bufferedReader()) {

        readLine().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }

        repeat(n) {
            val row = readLine().split(' ').map {
                if (it == "1") -1
                else it.toInt()
            }
            if (row.contains(2)) {
                target = Pos(x = it, y = row.indexOf(2))
            }
            board.add(row.toMutableList())
        }

        bfs(target.x, target.y)

        val sb = StringBuilder()
        repeat(n) { x ->
            repeat(m) { y ->
                sb.append("${board[x][y]} ")
            }
            sb.appendLine()
        }

        print(sb)
    }

    private fun bfs(_x: Int, _y: Int) {
        val queue = ArrayDeque<Pos>()
        queue.add(Pos(_x, _y))
        board[_x][_y] = 0

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx in 0 until n && ny in 0 until m) {
                    if (board[nx][ny] == -1) {
                        queue.add(Pos(nx, ny))
                        board[nx][ny] = board[x][y] + 1
                    }
                }
            }
        }
    }
}

fun main() {
    `쉬운 최단거리`().solve()
}
