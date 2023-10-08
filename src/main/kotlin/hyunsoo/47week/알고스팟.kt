package hyunsoo.`47week`

/**
 *
 * <문제>
 * [알고스팟](https://www.acmicpc.net/problem/1261)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_알고스팟` {

    private data class Position(val x: Int, val y: Int)

    private data class Bundle(val pos: Position, val cost: Int) {
        constructor(x: Int, y: Int, cost: Int) : this(Position(x, y), cost)
    }

    private val dirs = listOf(
        Position(1, 0),
        Position(0, 1),
        Position(-1, 0),
        Position(0, -1),
    )

    fun solution() {

        val deque = ArrayDeque<Bundle>()

        val (width, height) = readln().split(" ").map { it.toInt() }

        val map = Array(height) {
            readln().chunked(1).map {
                if (it == "1") WALL else it.toInt()
            }.toIntArray()

        }

        val visited = Array(height) {
            BooleanArray(width)
        }

        deque.add(Bundle(0, 0, 0))
        visited[0][0] = true

        while (deque.isNotEmpty()) {

            val (pos, cost) = deque.removeFirst()

            map[pos.x][pos.y] = cost
            dirs.forEach { dir ->

                val nx = pos.x + dir.x
                val ny = pos.y + dir.y

                if (nx !in 0 until height ||
                    ny !in 0 until width ||
                    visited[nx][ny]
                ) return@forEach

                visited[nx][ny] = true

                when (map[nx][ny]) {
                    WALL -> {
                        deque.addLast(Bundle(nx, ny, cost + 1))
                    }

                    EMPTY -> {
                        deque.addFirst(Bundle(nx, ny, cost))
                    }
                }
            }

        }

        println(map[height - 1][width - 1])
    }

    companion object {
        const val EMPTY = 0
        const val WALL = -1
    }
}

fun main() {
    전현수_알고스팟().solution()
}