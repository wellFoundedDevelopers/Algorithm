package hyunsoo.`58week`

import java.util.LinkedList
import java.util.Queue
import java.util.Stack

/**
 *
 * <문제>
 * [양치기 꿍](https://www.acmicpc.net/problem/3187)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_양치기_꿍` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, 1),
        Position(0, -1),
    )

    private val board = mutableListOf<MutableList<String>>()

    fun solution() {

        val (r, c) = readln().split(" ").map { it.toInt() }

        val visited = Array(r) {
            BooleanArray(c)
        }

        repeat(r) {
            val row = readln().chunked(1) as MutableList
            board.add(row)
        }

        for (i in 0 until r) {
            for (j in 0 until c) {

                val cur = board[i][j]
                if (cur == WALL || cur == EMPTY) continue

                val sheepStack = Stack<Position>()
                val wolfStack = Stack<Position>()

                val queue: Queue<Position> = LinkedList()
                queue.add(Position(i, j))

                investigate(queue, sheepStack, wolfStack, visited)

                if (wolfStack.size == 0 || sheepStack.size == 0) continue

                if (wolfStack.size < sheepStack.size) consume(wolfStack)
                else consume(sheepStack)

            }
        }

        val flattedBoard = board.flatten()
        println(
            "${flattedBoard.count { it == SHEEP }} ${flattedBoard.count { it == WOLF }}"
        )
    }

    private fun investigate(
        queue: Queue<Position>,
        sheepStack: Stack<Position>,
        wolfStack: Stack<Position>,
        visited: Array<BooleanArray>,
    ) {
        while (queue.isNotEmpty()) {

            val curPos = queue.poll()
            val curInfo = board[curPos.x][curPos.y]

            if (visited[curPos.x][curPos.y]) continue
            visited[curPos.x][curPos.y] = true

            if (curInfo == WOLF) wolfStack.add(Position(curPos.x, curPos.y))
            else if (curInfo == SHEEP) sheepStack.add(Position(curPos.x, curPos.y))

            dirs.forEach { dir ->
                val nx = curPos.x + dir.x
                val ny = curPos.y + dir.y

                if (nx !in 0 until board.size ||
                    ny !in 0 until board.first().size ||
                    board[nx][ny] == WALL ||
                    visited[nx][ny]
                ) return@forEach

                queue.add(Position(nx, ny))
            }
        }
    }

    private fun consume(stack: Stack<Position>) {

        while (stack.isNotEmpty()) {
            val curPos = stack.pop()
            board[curPos.x][curPos.y] = "."
        }

    }

    companion object {
        const val WALL = "#"
        const val EMPTY = "."
        const val WOLF = "v"
        const val SHEEP = "k"
    }
}

fun main() {
    전현수_양치기_꿍().solution()
}