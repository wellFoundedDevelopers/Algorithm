package heejik.`35week`

import kotlin.math.abs
import kotlin.properties.Delegates

class `인구 이동` {

    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    var n by Delegates.notNull<Int>()
    var l by Delegates.notNull<Int>()
    var r by Delegates.notNull<Int>()
    val populations = mutableListOf<MutableList<Int>>()
    lateinit var moved: MutableList<MutableList<Boolean>>


    fun solve() {
        setting()

        var cnt = 0
        while (true) {
            if (move().not()) break

            moved.forEach {
                it.fill(false)
            }
            cnt++
        }

        println(cnt)
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            l = this[1]
            r = this[2]
        }

        moved = MutableList(n) { MutableList(n) { false } }

        repeat(n) {
            val population = readln().split(' ').map { it.toInt() }.toMutableList()
            populations.add(population)
        }
    }


    private fun move(): Boolean {
        var isMoved = false

        repeat(n) { x ->
            repeat(n) { y ->
                if (moved[x][y].not()) {
                    if (bfs(Pos(x, y))) isMoved = true
                }
            }
        }

        return isMoved
    }

    private fun bfs(_pos: Pos): Boolean {
        var s = populations[_pos.x][_pos.y]
        val union = mutableSetOf<Pos>(_pos)
        val queue = ArrayDeque<Pos>()
        queue.add(_pos)
        moved[_pos.x][_pos.y] = true

        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            val x = pos.x
            val y = pos.y

            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx !in 0 until n || ny !in 0 until n) continue
                if (abs(populations[x][y] - populations[nx][ny]) !in l..r) continue
                if (moved[nx][ny]) continue

                s += populations[nx][ny]
                queue.add(Pos(nx, ny))
                union.add(Pos(nx, ny))
                moved[nx][ny] = true
            }
        }
        if (union.size > 1) {
            val newPopulation = s / union.size

            union.forEach {
                populations[it.x][it.y] = newPopulation
            }
            return true
        }
        return false
    }
}

fun main() {
    `인구 이동`().solve()
}