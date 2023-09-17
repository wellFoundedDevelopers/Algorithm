package hyunsoo.`45week`

import java.lang.Integer.max
import kotlin.math.min

/**
 *
 * <문제>
 * [게임](https://www.acmicpc.net/problem/1584)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_게임` {

    private data class Position(val x: Int, val y: Int)
    private data class BFSData(val pos: Position, val cost: Int) {
        constructor(x: Int, y: Int, cost: Int) : this(Position(x, y), cost)
    }

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )

    private val mapInfo = Array(501) {
        IntArray(501)
    }

    private val visited = Array(501) {
        BooleanArray(501)
    }

    fun solution() {

        val deque = ArrayDeque<BFSData>()
        deque.add(BFSData(0, 0, 0))

        repeat(readln().toInt()) {
            val (dx1, dy1, dx2, dy2) = readln().split(" ").map { it.toInt() }

            val smallX = min(dx1, dx2)
            val bigX = max(dx1, dx2)
            val smallY = min(dy1, dy2)
            val bigY = max(dy1, dy2)

            for (x in smallX..bigX) {
                for (y in smallY..bigY) {
                    mapInfo[x][y] = 1
                }
            }
        }

        repeat(readln().toInt()) {
            val (dx1, dy1, dx2, dy2) = readln().split(" ").map { it.toInt() }

            val smallX = min(dx1, dx2)
            val bigX = max(dx1, dx2)
            val smallY = min(dy1, dy2)
            val bigY = max(dy1, dy2)

            for (x in smallX..bigX) {
                for (y in smallY..bigY) {
                    mapInfo[x][y] = DANGEROUS_ZONE
                }
            }
        }

        while (deque.isNotEmpty()) {

            val currentData = deque.removeFirst()

            val currentPosition = currentData.pos
            if (visited[currentPosition.x][currentPosition.y]) continue

            mapInfo[currentPosition.x][currentPosition.y] = currentData.cost
            visited[currentPosition.x][currentPosition.y] = true

            dirs.forEach { dir ->

                val nx = currentPosition.x + dir.x
                val ny = currentPosition.y + dir.y

                if (nx !in 0..500 ||
                    ny !in 0..500
                ) return@forEach

                val nextPos = mapInfo[nx][ny]

                if (nextPos == DANGEROUS_ZONE) return@forEach

                if (nextPos == 0) deque.addFirst(BFSData(nx, ny, currentData.cost))
                else deque.addLast(BFSData(nx, ny, currentData.cost + 1))

            }
        }

        println(
            if (visited[500][500]) {
                mapInfo[500][500]
            } else -1
        )

    }

    companion object {
        const val DANGEROUS_ZONE = -1
    }
}

fun main() {
    전현수_게임().solution()
}