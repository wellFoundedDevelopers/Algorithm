package heejik.`20week`

import java.lang.Integer.max
import kotlin.properties.Delegates

class `안전 영역` {

    data class Pos(
        val x: Int,
        val y: Int
    )

    var n by Delegates.notNull<Int>()
    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    val area = mutableListOf<MutableList<Int>>()
    val copiedArea = mutableListOf<MutableList<Int>>()

    fun solve() {
        n = readln().toInt()
        var answer = 0

        repeat(n) {
            area.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        for (height in 0..area.maxOf { it.max() }) {
            answer = max(answer, findSafeArea(height))
        }

        println(answer)
    }

    private fun findSafeArea(height: Int): Int {
        var cnt = 0
        copiedArea.clear()
        area.forEach { copiedArea.add(it.toMutableList()) }
        copiedArea.forEachIndexed { x, row ->
            row.forEachIndexed { y, space ->
                if (space > height) {
                    setVisited(x, y, height)
                    cnt++
                }
            }
        }
        return cnt
    }

    private fun setVisited(_x: Int, _y: Int, height: Int, ) {
        val queue = ArrayDeque<Pos>()
        queue.add(Pos(_x, _y))
        copiedArea[_x][_y] = 0
        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx !in 0 until n || ny !in 0 until n) continue
                if (copiedArea[nx][ny] <= height) continue
                copiedArea[nx][ny] = 0
                queue.add(Pos(nx, ny))
            }
        }
    }
}

fun main() {
    `안전 영역`().solve()
}
