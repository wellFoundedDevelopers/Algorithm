package heejik.`31week`

import kotlin.math.abs
import kotlin.properties.Delegates

class 인구이동 {

    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)

    var n by Delegates.notNull<Int>()
    var l by Delegates.notNull<Int>()
    var r by Delegates.notNull<Int>()
    private val board = mutableListOf<MutableList<Int>>()
    private lateinit var visited: MutableList<BooleanArray>

    fun solve() {
        setting()
        repeat(2001) { cnt ->
            var isMoved = false
            visited.forEach {
                it.fill(false)
            }

            repeat(n) { x ->
                repeat(n) { y ->
                    if (visited[x][y].not()) {
                        isMoved = move(_pos = Pos(x, y)) || isMoved
                    }
                }
            }

            if (isMoved.not()) {
                println(cnt)
                return
            }
        }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            l = this[1]
            r = this[2]
        }

        visited = MutableList(n) { BooleanArray(n) }

        repeat(n) {
            board.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }
    }

    private fun move(_pos: Pos): Boolean {
        val queue = ArrayDeque<Pos>()
        val canMovePos = mutableListOf(_pos)
        queue.add(_pos)
        visited[_pos.x][_pos.y] = true

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx !in 0 until n || ny !in 0 until n) continue
                if (visited[nx][ny]) continue
                if (abs(board[x][y] - board[nx][ny]) in l..r) {
                    val newPos = Pos(nx, ny)
                    canMovePos.add(newPos)
                    visited[nx][ny] = true
                    queue.add(newPos)
                }
            }
        }

        if (canMovePos.size == 1) return false

        val newPopulation = canMovePos.sumOf { board[it.x][it.y] } / canMovePos.size

        canMovePos.forEach {
            board[it.x][it.y] = newPopulation
        }

        return true
    }
}

fun main() {
    인구이동().solve()
}