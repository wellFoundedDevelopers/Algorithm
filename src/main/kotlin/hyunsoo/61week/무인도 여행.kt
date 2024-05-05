package hyunsoo.`61week`

import java.util.*

/**
 *
 * <문제>
 * [무인도 여행](https://school.programmers.co.kr/learn/courses/30/lessons/154540)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_무인도_여행` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )

    fun solution(maps: Array<String>): List<Int> {
        val answer = mutableListOf<Int>()

        val validMap = maps.map { row ->
            row.chunked(1).map {
                if (it == "X") -1 else it.toInt()
            } as MutableList
        }

        for (i in maps.indices) {
            for (j in maps.first().indices) {

                if (validMap[i][j] == -1) continue

                var curStayCnt = 0
                val queue: Queue<Position> = LinkedList()
                queue.add(Position(i, j))

                while (queue.isNotEmpty()) {
                    val curPos = queue.poll()

                    if (validMap[curPos.x][curPos.y] == -1) continue

                    curStayCnt += validMap[curPos.x][curPos.y]
                    validMap[curPos.x][curPos.y] = -1

                    dirs.forEach { dir ->
                        val nx = curPos.x + dir.x
                        val ny = curPos.y + dir.y

                        if (nx !in maps.indices ||
                            ny !in maps.first().indices ||
                            validMap[nx][ny] == -1
                        ) return@forEach

                        queue.add(Position(nx, ny))
                    }
                }

                if (curStayCnt != 0) {
                    answer.add(curStayCnt)
                }
            }

        }

        return if (answer.isEmpty()) listOf(-1) else answer.sortedBy { it }
    }
}

fun main() {
    전현수_무인도_여행().solution(
        arrayOf(
            "X591X", "X1X5X", "X231X", "1XXX1"
        )
    ).apply {
        println(this)
    }
}