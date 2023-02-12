package heejik.`21week`

import kotlin.properties.Delegates

class 알파벳 {

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    var r by Delegates.notNull<Int>()
    var c by Delegates.notNull<Int>()
    private val board = mutableListOf<CharArray>()
    private var answer = 0
    fun solve() {
        readln().split(' ').map { it.toInt() }.apply {
            r = this.first()
            c = this.last()
        }

        repeat(r) {
            board.add(readln().toCharArray())
        }

        dfs(0, 0, charArrayOf(board[0][0]), 1)

        println(answer)
    }

    fun dfs(x: Int, y: Int, visited: CharArray, cnt: Int) {
        answer = answer.coerceAtLeast(cnt)

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx !in 0 until r || ny !in 0 until c) continue
            if (board[nx][ny] in visited) continue
            dfs(nx, ny, visited.plus(board[nx][ny]), cnt + 1)
        }
    }
}

fun main() {
    알파벳().solve()
}