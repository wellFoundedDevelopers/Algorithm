package hyunsoo.`61week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 *
 * <문제>
 * [도넛 행성](https://www.acmicpc.net/problem/27211)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_도넛_행성` {

    private val br = BufferedReader(InputStreamReader(System.`in`))

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )

    private val board = mutableListOf<MutableList<Int>>()

    fun solution() {

        var ans = 0
        val (n, m) = br.readLine().split(" ").map { it.toInt() }

        repeat(n) {
            val row = br.readLine().split(" ").map { it.toInt() } as MutableList
            board.add(row)
        }

        for (i in 0 until n) {
            for (j in 0 until m) {

                if (board[i][j] != EMPTY) continue

                val queue: Queue<Position> = LinkedList()
                queue.add(Position(i, j))

                while (queue.isNotEmpty()) {

                    val cur = queue.poll()
                    board[cur.x][cur.y] = VISITED

                    dirs.forEach { dir ->
                        val nx = (cur.x + dir.x).let {
                            if (it < 0) board.size - 1
                            else if (board.size <= it) 0
                            else it
                        }
                        val ny = (cur.y + dir.y).let {
                            if (it < 0) board.first().size - 1
                            else if (board.first().size <= it) 0
                            else it
                        }

                        if (board[nx][ny] == EMPTY) {
                            queue.add(Position(nx, ny))
                            board[nx][ny] = VISITED
                        }
                    }
                }

                ans++

            }
        }

        println(ans)

    }

    companion object {
        private const val BLOCKED = 1
        private const val VISITED = -1
        private const val EMPTY = 0
    }
}

fun main() {
    전현수_도넛_행성().solution()
}