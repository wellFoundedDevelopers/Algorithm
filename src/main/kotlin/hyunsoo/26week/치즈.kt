package hyunsoo.`26week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [치즈](https://www.acmicpc.net/problem/2636)
 *
 * - 아이디어
 *
 * 모든 판이 화이트가 될 때까지 완팀을하고, 흰 곳이 발견되면 거기로 부터 bfs?
 * - 위의 방식으로 구현하면 치즈의 구멍에서부터도 치즈가 녹게되는 현상이 발생함.
 *
 * - 트러블 슈팅
 *
 */
class `전현수_치즈` {

    private data class Position(val x: Int, val y: Int) {

        companion object {
            val startPosition = Position(0, 0)
        }

    }

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, 1),
        Position(0, -1),
    )

    private var row = 0
    private var column = 0

    private val board = mutableListOf<MutableList<Int>>()
    private var cntRightBeforeMelt = 0
    private var requiredTime = 0

    fun solution() {

        readln().split(" ").map { it.toInt() }.apply {
            row = first()
            column = last()
        }

        repeat(row) {
            val rowData = readln().split(" ").map { it.toInt() }.toMutableList()
            board.add(rowData)
        }

        cntRightBeforeMelt = this.cheeseCnt
        checkAir()

        println(requiredTime)
        println(cntRightBeforeMelt)

    }

    private fun checkAir() {

        requiredTime++

        val queue: Queue<Position> = LinkedList()
        queue.add(Position.startPosition)

        while (queue.isNotEmpty()) {

            val pos = queue.poll()

            dirs.forEach { dir ->

                val nx = pos.x + dir.x
                val ny = pos.y + dir.y

                // 범위 초과 시 탐색 넘어감
                if (nx !in 0 until row || ny !in 0 until column) return@forEach

                val curInfo = board[nx][ny]

                if (curInfo == EMPTY) {
                    board[nx][ny] = AIR
                    queue.add(Position(nx, ny))
                } else if (curInfo == CHEESE) {
                    board[nx][ny] = CHEESE_THAT_WILL_BE_MELT
                }

            }

        }

        val curCheeseCnt = this.cheeseCnt

        if (curCheeseCnt != 0) {
            cntRightBeforeMelt = curCheeseCnt
            melt()
            checkAir()
        }

    }

    private fun melt() {
        for (i in 0 until row) {
            for (j in 0 until column) {
                val curInfo = board[i][j]
                if (curInfo == CHEESE_THAT_WILL_BE_MELT ||
                    curInfo == AIR
                ) board[i][j] = EMPTY
            }
        }
    }

    private val cheeseCnt
        get() = board.sumOf { rowData ->
            rowData.count {
                it == CHEESE
            }
        }

    companion object {
        const val EMPTY = 0
        const val AIR = 3
        const val CHEESE = 1
        const val CHEESE_THAT_WILL_BE_MELT = -1
    }
}

fun main() {
    전현수_치즈().solution()
}