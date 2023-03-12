package heejik.`26week`

import kotlin.properties.Delegates

class 치즈 {

    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    val board = mutableListOf<MutableList<Int>>()
    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }

        repeat(n) {
            board.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        val answers = mutableListOf<Int>()

        while (true) {
            val answer = bfs(pos = Pos(0, 0))
            if (answer == 0) {
                break
            }
            answers.add(answer)
        }
        println(answers.size)
        println(answers.last())
    }

    fun bfs(pos: Pos): Int {
        var cnt = 0
        val queue = ArrayDeque<Pos>()
        queue.add(pos)
        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if ((nx !in 0 until n) or (ny !in 0 until m)) continue
                if (board[nx][ny] == 1) {
                    board[nx][ny] = -1
                    cnt++
                }
                if (board[nx][ny] == 0) {
                    queue.add(Pos(nx, ny))
                }
                board[nx][ny] = -1
            }
        }

        board.forEachIndexed { x, ints ->
            ints.forEachIndexed { y, i ->
                if (i == -1) {
                    board[x][y] = 0
                }
            }
        }


        return cnt
    }
}

fun main() {
    치즈().solve()

}