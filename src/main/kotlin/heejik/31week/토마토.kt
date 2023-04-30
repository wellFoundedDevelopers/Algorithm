package heejik.`31week`

import kotlin.properties.Delegates

class 토마토 {

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, -1, 1)

    data class Pos(
        val x: Int, val y: Int
    )

    var m by Delegates.notNull<Int>()
    var n by Delegates.notNull<Int>()
    val board = mutableListOf<MutableList<Int>>()
    private var ripenedTomatoes = ArrayDeque<Pos>()
    private var day = 0

    fun solve() {
        setting()
        while (true) {
            ripenedTomatoes = ripenTomatoes(ripenedTomatoes)
            if (ripenedTomatoes.isEmpty()) break
            day++
        }
        println(if (isAllRipened()) day else -1)
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            m = this[0]
            n = this[1]
        }
        repeat(n) { x ->
            board.add(readln().split(' ').map { it.toInt() }.run {
                forEachIndexed { y, state ->
                    if (state == 1) {
                        ripenedTomatoes.add(Pos(x, y))
                    }
                }
                toMutableList()
            })
        }
    }

    private fun ripenTomatoes(preRipenedTomatoes: ArrayDeque<Pos>): ArrayDeque<Pos> {
        val ripenedTomatoes = ArrayDeque<Pos>()
        while (preRipenedTomatoes.isNotEmpty()) {
            val ripenedTomato = preRipenedTomatoes.removeFirst()
            for (i in dx.indices) {
                val nx = ripenedTomato.x + dx[i]
                val ny = ripenedTomato.y + dy[i]
                if (nx !in 0 until n || ny !in 0 until m) continue
                if (board[nx][ny] == 0) {
                    board[nx][ny] = 1
                    ripenedTomatoes.add(Pos(nx, ny))
                }
            }
        }
        return ripenedTomatoes
    }

    private fun isAllRipened(): Boolean {
        return board.all { it.contains(0).not() }
    }
}

fun main() {
    토마토().solve()
}