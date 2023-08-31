package hyunsoo.`43week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [쉬운 최단거리](https://www.acmicpc.net/problem/14940)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_쉬운_최단거리` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(1, 0),
        Position(-1, 0),
        Position(0, 1),
        Position(0, -1),
    )

    private lateinit var map: Array<MutableList<Int>>
    private lateinit var targetPosition: Position

    fun solution() {

        val (n, m) = readln().split(" ").map { it.toInt() }

        map = Array(n) { xIndex ->
            readln().split(" ").mapIndexed { yIndex, value ->
                val intValue = value.toInt()
                when (intValue) {
                    2 -> {
                        targetPosition = Position(xIndex, yIndex)
                        2
                    }

                    0 -> 0
                    else -> INIT
                }
            } as MutableList
        }

        val queue: Queue<Pair<Position, Int>> = LinkedList()
        queue.add(targetPosition to 1)
        map[targetPosition.x][targetPosition.y] = 0

        while (queue.isNotEmpty()) {

            val (curPos, distance) = queue.poll()

            dirs.forEach { dir ->

                val nx = curPos.x + dir.x
                val ny = curPos.y + dir.y

                if (nx !in 0 until n ||
                    ny !in 0 until m ||
                    map[nx][ny] != INIT
                ) return@forEach

                map[nx][ny] = distance
                queue.add(Position(nx, ny) to distance + 1)

            }

        }

        map.forEach { row ->
            row.map { if (it == INIT) -1 else it }.joinToString(" ").apply {
                println(this)
            }
        }
    }

    companion object {
        private const val INIT = 9999
    }
}

fun main() {
    전현수_쉬운_최단거리().solution()
}