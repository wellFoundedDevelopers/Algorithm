package heejik.`29week`

import kotlin.math.max
import kotlin.properties.Delegates

class 빙산 {

    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    val pole = mutableListOf<MutableList<Int>>()
    lateinit var visited: MutableList<MutableList<Boolean>>

    fun solve() {
        var cnt = 0
        setting()
        while (true) {
            val result = isEnd()
            if (result.first) {
                if (result.second) {
                    cnt = 0
                }
                break
            }
            cnt++
            melt()
        }

        println(cnt)
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        visited = MutableList(n) { MutableList(m) { false } }
        repeat(n) {
            pole.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }
    }

    private fun melt() {
        val offset = MutableList(n) { MutableList(m) { 0 } }
        repeat(n) row@{ x ->
            repeat(m) column@{ y ->
                val height = pole[x][y]
                if (height == 0) return@column
                for (i in dx.indices) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if (pole[nx][ny] == 0) offset[x][y]--
                }
            }
        }

        repeat(n) row@{ x ->
            repeat(m) column@{ y ->
                pole[x][y] = max(0, offset[x][y] + pole[x][y])
            }
        }
    }


    private fun isEnd(): Pair<Boolean, Boolean> {
        var cnt = 0
        repeat(n) { x ->
            repeat(m) { y ->
                if ((pole[x][y] > 0) and (visited[x][y].not())) {
                    cnt++
                    getChunk(x, y)
                }
            }
        }

        visited.forEach {
            it.fill(false)
        }

        return if (cnt == 0) {
            Pair(true, true)
        } else if (cnt >= 2) {
            Pair(true, false)
        } else {
            Pair(false, false)
        }
    }

    private fun getChunk(x: Int, y: Int) {
        val queue = ArrayDeque<Pos>()
        queue.add(Pos(x, y))

        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            for (i in dx.indices) {
                val nx = pos.x + dx[i]
                val ny = pos.y + dy[i]
                if ((pole[nx][ny] != 0) and visited[nx][ny].not()) {
                    queue.add(Pos(nx, ny))
                    visited[nx][ny] = true
                }
            }
        }
    }
}


fun main() {
    빙산().solve()
}