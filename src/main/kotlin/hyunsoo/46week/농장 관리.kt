package hyunsoo.`46week`

import java.util.*

/**
 *
 * <문제>
 * [농장 관리](https://www.acmicpc.net/problem/1245)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_농장_관리` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, 1),
        Position(0, -1),
        Position(-1, -1),
        Position(-1, 1),
        Position(1, -1),
        Position(1, 1),
    )

    private lateinit var farm: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>

    private val queue: Queue<Position> = LinkedList()

    private var cnt = 0
    private var isPeak = false

    fun solution() {

        val (n, m) = readln().split(" ").map { it.toInt() }

        farm = Array(n) {
            readln().split(" ").map { it.toInt() }.toIntArray()
        }
        visited = Array(n) {
            BooleanArray(m)
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (visited[i][j] || farm[i][j] == EMPTY) continue

                isPeak = true
                bfs(i, j)
                if (isPeak) cnt++
            }
        }

        println(cnt)

    }

    private fun bfs(x: Int, y: Int) {

        val currentHeight = farm[x][y]
        queue.add(Position(x, y))
        visited[x][y] = true

        while (queue.isNotEmpty()) {

            val pos = queue.poll()

            dirs.forEach { dir ->
                val nx = pos.x + dir.x
                val ny = pos.y + dir.y

                if (nx !in farm.indices ||
                    ny !in farm.first().indices
                ) return@forEach

                if (currentHeight < farm[nx][ny]) isPeak = false
                if (farm[nx][ny] != currentHeight ||
                    farm[nx][ny] == 0 ||
                    visited[nx][ny]
                )
                    return@forEach

                queue.add(Position(nx, ny))
                visited[nx][ny] = true


            }
        }

    }

    companion object {
        const val EMPTY = 0
    }
}

fun main() {
    전현수_농장_관리().solution()
}