package heejik.`61week`

class `도넛 행성` {

    data class Pos(
        val x: Int,
        val y: Int
    )

    var answer = 0
    var n = -1
    var m = -1
    val board = mutableListOf<MutableList<Int>>()
    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        repeat(n) {
            board.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (board[i][j] == 0) {
                    bfs(Pos(i, j))
                    answer += 1
                }
            }
        }

        println(answer)
    }

    private fun bfs(pos: Pos) {
        val queue = ArrayDeque<Pos>()
        queue.add(pos)

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in 0..3) {
                val nx = (x + dx[i] + n) % n
                val ny = (y + dy[i] + m) % m
                if (board[nx][ny] == 1) continue
                board[nx][ny] = 1
                queue.add(Pos(nx, ny))
            }
        }
    }
}


fun main() {
    `도넛 행성`().solve()
}