package hyunsoo.`62week`

import java.util.*

/**
 *
 * <문제>
 * [경쟁적 전염](https://www.acmicpc.net/problem/18405)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_경쟁적_전염` {

    private data class Position(val x: Int, val y: Int)

    private data class VirusData(val pos: Position, val num: Int, val time: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )

    private val board = mutableListOf<MutableList<Int>>()
    private val virusNumList = mutableListOf<VirusData>()
    fun solution() {

        val (n, k) = readln().split(" ").map { it.toInt() }

        repeat(n) { rowIndex ->
            board.add(readln().split(" ").mapIndexed { colIndex, numString ->
                numString.toInt().apply {
                    if (this != 0) virusNumList.add(
                        VirusData(Position(rowIndex, colIndex), this, 0)
                    )
                }
            } as MutableList)
        }

        val (time, targetX, targetY) = readln().split(" ").map { it.toInt() }

        val queue: Queue<VirusData> = LinkedList()

        virusNumList.sortedBy { it.num }.forEach {
            queue.add(it)
        }

        while (queue.isNotEmpty()) {

            val (curPos, curNum, curTime) = queue.poll()

            if (time <= curTime) break

            dirs.forEach {
                val nx = curPos.x + it.x
                val ny = curPos.y + it.y

                if (nx !in 0 until n ||
                    ny !in 0 until n ||
                    board[nx][ny] != 0
                ) return@forEach

                board[nx][ny] = curNum
                queue.add(
                    VirusData(Position(nx, ny), curNum, curTime + 1)
                )
            }
        }

        println(board[targetX - 1][targetY - 1])
    }
}

fun main() {
    전현수_경쟁적_전염().solution()
}