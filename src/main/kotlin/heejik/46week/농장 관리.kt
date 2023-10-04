package heejik.`46week`

import kotlin.properties.Delegates

class `농장 관리` {

    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0, 1, 1, -1, -1)
    val dy = listOf(0, 0, 1, -1, 1, -1, 1, -1)

    val board = mutableListOf<MutableList<Int>>()
    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    fun solve() {
        var answer = 0

        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }

        repeat(n) {
            board.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        while (true) {
            val maxHeight = board.maxOf { it.max() }
            if (maxHeight == 0) break

            var x = 0
            var y = 0

            for (i in 0 until n){
                for (j in 0 until m) {
                    if (board[i][j] == maxHeight) {
                        x = i
                        y = j
                    }
                }
            }
            bfs(Pos(x,y))
            answer++
        }
        println(answer)
    }

    fun bfs(_pos: Pos) {
        val queue = ArrayDeque<Pair<Pos,Int>>()
        queue.add(_pos to board[_pos.x][_pos.y])
        board[_pos.x][_pos.y] = 0

        while (queue.isNotEmpty()) {
            val (pos, preHeight) = queue.removeFirst()
            repeat(dx.size) { i ->
                val nx = pos.x + dx[i]
                val ny = pos.y + dy[i]
                if (nx in 0 until n && ny in 0 until m) {
                    if (board[nx][ny] != 0 && board[nx][ny] <= preHeight) {
                        queue.add(Pos(nx, ny) to board[nx][ny])
                        board[nx][ny] = 0
                    }
                }
            }
        }
    }
}

fun main() {
    `농장 관리`().solve()
}