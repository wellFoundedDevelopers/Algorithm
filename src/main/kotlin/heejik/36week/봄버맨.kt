package heejik.`36week`

import kotlin.properties.Delegates

class 봄버맨 {

    val dx = listOf(1, -1, 0, 0, 0)
    val dy = listOf(0, 0, 1, -1, 0)

    data class Pos(
        val x: Int,
        val y: Int
    )

    var r by Delegates.notNull<Int>()
    var c by Delegates.notNull<Int>()
    var n by Delegates.notNull<Int>()

    val board = mutableListOf<MutableList<Int>>()

    fun solve() {
        setting()
        repeat(n - 1) {
            timePass()
        }

        board.forEach {
            it.forEach { time ->
                print(if (time ==0 ) '.' else 'O')
            }
            println()
        }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            r = this[0]
            c = this[1]
            n = this[2]
        }

        repeat(r) {
            val row = readln().map {
                if (it == '.') 0 else 2
            }.toMutableList()
            board.add(row)
        }
    }

    private fun timePass() {
        val bombsToExplode = mutableListOf<Pos>()
        repeat(r) { x ->
            repeat(c) { y ->
                board[x][y]++
                if (board[x][y] == 4)
                    bombsToExplode.add(Pos(x, y))
            }
        }
        bombsToExplode.forEach {
            explode(it)
        }
    }

    private fun explode(pos: Pos) {
        for (i in dx.indices) {
            val nx = pos.x + dx[i]
            val ny = pos.y + dy[i]

            if (nx !in 0 until r || ny !in 0 until c) continue
            board[nx][ny] = 0
        }
    }
}

fun main() {
    봄버맨().solve()
}