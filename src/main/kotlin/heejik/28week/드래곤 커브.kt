package heejik.`28week`

import kotlin.properties.Delegates

class `드래곤 커브` {

    enum class Move(val pos: Pos) {
        RIGHT(Pos(0, 1)), UP(Pos(-1, 0)), LEFT(Pos(0, -1)), DOWN(Pos(1, 0));

        fun rotate(): Move {
            return Move.values().get((Move.values().indexOf(this) + 1) % 4)
        }
    }


    data class Pos(
        val x: Int,
        val y: Int
    )

    var n by Delegates.notNull<Int>()
    val board = MutableList(101) { MutableList(101) { false } }

    fun solve() {
        getInput()
        printAnswer()
    }

    private fun getInput() {
        n = readln().toInt()
        repeat(n) {
            val (y, x, d, g) = readln().split(' ').map { it.toInt() }
            drawDragonCurve(x, y, d, g)
        }
    }

    private fun drawDragonCurve(x: Int, y: Int, d: Int, g: Int) {
        val preMoves = mutableListOf(Move.values().get(d))
        val firstPos = Move.values().get(d).pos
        board[x][y] = true
        board[firstPos.x + x][firstPos.y + y] = true
        var lastPos = Pos(firstPos.x + x, firstPos.y + y)
        repeat(g) {
            val tmpMoves = mutableListOf<Move>()
            preMoves.forEach {
                board[it.rotate().pos.x + lastPos.x][it.rotate().pos.y + lastPos.y] = true
                lastPos = Pos(it.rotate().pos.x + lastPos.x, it.rotate().pos.y + lastPos.y)
                tmpMoves.add(it.rotate())
            }
            preMoves.reverse()
            preMoves.addAll(tmpMoves)
            preMoves.reverse()
        }
    }

    private fun printAnswer() {
        var cnt = 0
        (0 until 100).forEach first@{ x ->
            (0 until 100).forEach second@{ y ->
                if (board[x][y].not()) return@second
                if (board[x + 1][y].not()) return@second
                if (board[x][y + 1].not()) return@second
                if (board[x + 1][y + 1].not()) return@second
                cnt++
            }
        }

        println(cnt)
    }
}

fun main() {
    `드래곤 커브`().solve()
}