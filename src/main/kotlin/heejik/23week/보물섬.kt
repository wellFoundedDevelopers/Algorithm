package heejik.`23week`

import kotlin.properties.Delegates

class 보물섬 {

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    data class Pos(
        val x: Int,
        val y: Int,
    )

    var r by Delegates.notNull<Int>()
    var c by Delegates.notNull<Int>()
    val treasureMap = mutableListOf<CharArray>()
    lateinit var visited: List<BooleanArray>
    val queue = ArrayDeque<Pair<Pos, Int>>()
    var answer = 0

    fun sovle() {
        readln().split(' ').map { it.toInt() }.run {
            r = first()
            c = last()
        }

        repeat(r) {
            treasureMap.add(readln().toCharArray())
        }
        visited = List(r) { BooleanArray(c) }

        treasureMap.forEachIndexed { x, row ->
            row.forEachIndexed { y, c ->
                if (c == 'L') {
                    queue.add(Pair(Pos(x, y), 0))
                    visited[x][y] = true
                    bfs()
                    queue.clear()
                    visited.forEach {
                        it.fill(false)
                    }
                }
            }
        }
        println(answer)
    }

    fun bfs() {
        while (queue.isNotEmpty()) {
            val (pos, cnt) = queue.removeFirst()
            answer = answer.coerceAtLeast(cnt)

            for (i in dx.indices) {
                val nx = pos.x + dx[i]
                val ny = pos.y + dy[i]

                if (nx !in 0 until r || ny !in 0 until c) continue
                if (treasureMap[nx][ny] != 'L' || visited[nx][ny]) continue
                visited[nx][ny] = true
                queue.add(Pair(Pos(nx, ny), cnt + 1))
            }
        }
    }
}

fun main() {
    보물섬().sovle()
}