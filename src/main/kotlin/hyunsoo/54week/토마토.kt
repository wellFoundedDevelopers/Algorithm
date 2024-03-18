package hyunsoo.`54week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [토마토](https://www.acmicpc.net/problem/7576)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_토마토` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(0, -1),
        Position(-1, 0),
        Position(0, 1),
        Position(1, 0),
    )

    private data class QueueData(val pos: Position, val day: Int)

    private val box = mutableListOf<MutableList<Int>>()
    private val startPositionList = mutableListOf<Position>()

    fun solution() {

        val queue: Queue<QueueData> = LinkedList()

        val (n, m) = readln().split(" ").map { it.toInt() }

        repeat(m) { rowIndex ->
            val row = readln().split(" ")
                .mapIndexed { columnIndex, it ->
                    if (it == "1") startPositionList.add(Position(rowIndex, columnIndex))
                    it.toInt()
                } as MutableList
            box.add(row)
        }

        startPositionList.forEach {
            queue.add(QueueData(it, 1))
        }

        while (queue.isNotEmpty()) {

            val (curPos, curDay) = queue.poll()

            dirs.forEach { dir ->

                val nx = curPos.x + dir.x
                val ny = curPos.y + dir.y

                if (nx !in 0 until m ||
                    ny !in 0 until n
                ) return@forEach

                if (box[nx][ny] == 0) {

                    box[nx][ny] = curDay + 1
                    queue.add(QueueData(Position(nx, ny), curDay + 1))

                }
            }
        }

        val result = box.flatten()

        if (result.contains(0)) {
            println(-1)
        } else {
            println(result.maxOf { it } - 1)
        }

    }

}

fun main() {
    전현수_토마토().solution()
}