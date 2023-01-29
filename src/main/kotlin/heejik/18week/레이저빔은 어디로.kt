package heejik.`18week`

import java.lang.Exception
import kotlin.properties.Delegates

class `레이저빔은 어디로` {

    private var n by Delegates.notNull<Int>()
    private var r by Delegates.notNull<Int>()
    private lateinit var board: MutableList<MutableList<Boolean>>
    private lateinit var lager: Lager

    data class Lager(
        var x: Int,
        var y: Int,
        var direction: Direction,
    ) {
        enum class Direction(val dx: Int, val dy: Int) {
            TOP(dx = -1, dy = 0),
            RIGHT(dx = 0, dy = 1),
            BOTTOM(dx = 1, dy = 0),
            LEFT(dx = 0, dy = -1),
        }

        fun rotation() {
            val nextDirection = Direction.values()[(direction.ordinal + 1) % Direction.values().size]
            direction = nextDirection
        }

        fun move() {
            x += direction.dx
            y += direction.dy
        }
    }

    private fun verifyDirection(n: Int, x: Int, y: Int): Lager.Direction {
        return when {
            x == -1 -> Lager.Direction.BOTTOM
            x == n -> Lager.Direction.TOP
            y == -1 -> Lager.Direction.RIGHT
            y == n -> Lager.Direction.LEFT
            else -> throw Exception("에~?")
        }
    }


    fun solve() {
        repeat(readln().toInt()) {
            game()
        }
    }

    private fun game() {
        setting()
        start()
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = first()
            r = last()
        }
        board = MutableList(n) { MutableList(n) { false } }

        repeat(r) {
            val (x, y) = readln().split(' ').map { it.toInt() }
            board[x - 1][y - 1] = true
        }
        val (x, y) = readln().split(' ').map { it.toInt() }
        lager = Lager(x = x - 1, y = y - 1, direction = verifyDirection(n, x - 1, y - 1))
    }

    private fun start() {
        var distance = 0

        while (distance <= (n + 1) * (n + 1)) {
            lager.move()
            if (lager.x !in 0 until n || lager.y !in 0 until n) {
                println("${lager.x + 1} ${lager.y + 1}")
                return
            }
            if (board[lager.x][lager.y]) {
                lager.rotation()
            }
            distance++
        }

        println("0 0")
    }

}

fun main() {
    `레이저빔은 어디로`().solve()
}