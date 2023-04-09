package heejik.`30week`

import kotlin.math.max
import kotlin.properties.Delegates

class 테트로미노 {


    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    data class Pos(
        val x: Int,
        val y: Int
    )

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    val board = mutableListOf<List<Int>>()

    fun solve() {
        setting()
        println(getMaxSum())
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        repeat(n) {
            board.add(readln().split(' ').map { it.toInt() })
        }
    }

    private fun getMaxSum(): Int {
        var maxSum = 0
        repeat(n) { x ->
            repeat(m) { y ->
                maxSum = max(maxSum, getSum(Pos(x, y)))
            }
        }
        return maxSum
    }

    private fun getSum(_pos: Pos): Int {
        var sum = 0
        val queue = ArrayDeque<Triple<Pos, List<Pos>, Int>>()
        queue.add(Triple(_pos, listOf(_pos), board[_pos.x][_pos.y]))
        while (queue.isNotEmpty()) {
            val (pos, ls, s) = queue.removeFirst()
            if (ls.count() == 4) {
                sum = max(sum, s)
                continue
            }
            for (i in dx.indices) {
                val nx = pos.x + dx[i]
                val ny = pos.y + dy[i]
                if (nx !in 0 until n || ny !in 0 until m) continue
                if (Pos(nx, ny) in ls) continue
                queue.add(Triple(Pos(nx, ny), ls.plus(Pos(nx, ny)), s + board[nx][ny]))
                if (ls.count() == 2) {
                    queue.add(Triple(Pos(pos.x, pos.y), ls.plus(Pos(nx, ny)), s + board[nx][ny]))
                }
            }
        }

        return sum
    }
}

fun main() {
    테트로미노().solve()
}