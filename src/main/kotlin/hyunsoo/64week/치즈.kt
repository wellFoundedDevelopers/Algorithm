package hyunsoo.`64week`

import java.util.*

/**
 *
 * <문제>
 * [치즈](https://www.acmicpc.net/problem/2638)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_치즈` {

    private data class Position(val x: Int, val y: Int)

    private val board = mutableListOf<MutableList<Int>>()

    private val dirs = listOf(
        Position(0, 1),
        Position(0, -1),
        Position(1, 0),
        Position(-1, 0),
    )

    fun solution() {

        val (n, m) = readln().split(" ").map { it.toInt() }

        repeat(n) {
            val row = readln().split(" ").map {
                if (it == "1") 3 else 0
            } as MutableList
            board.add(row)
        }

        var cnt = 0

        while (board.flatten().any { it != 0 }) {

            val queue: Queue<Position> = LinkedList()
            val outsideAirStack = Stack<Position>()
            val visited = Array(n) {
                BooleanArray(m)
            }

            queue.add(Position(0, 0))
            visited[0][0] = true

            while (queue.isNotEmpty()) {

                val cur = queue.poll()

                dirs.forEach {
                    val nx = it.x + cur.x
                    val ny = it.y + cur.y

                    if (nx !in 0 until n ||
                        ny !in 0 until m ||
                        visited[nx][ny]
                    ) return@forEach

                    if (board[nx][ny] == 0) {
                        val next = Position(nx, ny)
                        outsideAirStack.add(next)
                        queue.add(next)
                        visited[nx][ny] = true
                    }
                }
            }

            check(outsideAirStack)
            board.melt()

            cnt++

        }

        println(cnt)
    }

    private fun check(stack: Stack<Position>) {
        while (stack.isNotEmpty()) {
            val (i, j) = stack.pop()
            val cur = board[i][j]
            if (cur == 0) {

                dirs.forEach {
                    val nx = i + it.x
                    val ny = j + it.y

                    if (nx !in board.indices ||
                        ny !in board.first().indices
                    ) return@forEach

                    if (0 < board[nx][ny]) board[nx][ny]--
                }
            }
        }
    }

    private fun MutableList<MutableList<Int>>.melt() {
        for (i in this.indices) {
            for (j in this.first().indices) {
                val cur = this[i][j]
                if (cur == 1) this[i][j] = 0
                else if (cur == 2) this[i][j] = 3
            }
        }
    }
}

fun main() {
    전현수_치즈().solution()
}