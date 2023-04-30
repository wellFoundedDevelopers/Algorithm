package heejik.`31week`

import kotlin.properties.Delegates

class `파이프 옮기기 1` {

    data class Pos(
        val x: Int,
        val y: Int
    )

    enum class Direction(val movePos: Pos) {
        // 세로
        VERTICAL(Pos(1, 0)),

        // 가로
        HORIZONTAL(Pos(0, 1)),

        // 대각선
        DIAGONAL(Pos(1, 1)),
    }

    var n by Delegates.notNull<Int>()
    val board = mutableListOf<List<Int>>()
    var answer = 0

    fun solve() {
        setting()
        movePipe(Pos(0, 1), Direction.HORIZONTAL)
        println(answer)
    }

    private fun setting() {
        n = readln().toInt()
        repeat(n) {
            board.add(readln().split(' ').map { it.toInt() })
        }
    }

    private fun movePipe(pipePos: Pos, pipeDirection: Direction) {
        if (pipePos.x !in 0 until n || pipePos.y !in 0 until n) return
        when (pipeDirection) {
            Direction.HORIZONTAL, Direction.VERTICAL -> {
                if (board[pipePos.x][pipePos.y] == 1) return
            }
            else -> {
                if (board[pipePos.x][pipePos.y] == 1 ||
                    board[pipePos.x][pipePos.y - 1] == 1 ||
                    board[pipePos.x - 1][pipePos.y] == 1) return
            }
        }
        if (pipePos == Pos(n - 1, n - 1)) {
            answer++
            return
        }
        when (pipeDirection) {
            Direction.HORIZONTAL -> {
                movePipe(
                    Pos(pipePos.x + Direction.HORIZONTAL.movePos.x, pipePos.y + Direction.HORIZONTAL.movePos.y),
                    Direction.HORIZONTAL
                )

                movePipe(
                    Pos(pipePos.x + Direction.DIAGONAL.movePos.x, pipePos.y + Direction.DIAGONAL.movePos.y),
                    Direction.DIAGONAL
                )
            }

            Direction.VERTICAL -> {
                movePipe(
                    Pos(pipePos.x + Direction.VERTICAL.movePos.x, pipePos.y + Direction.VERTICAL.movePos.y),
                    Direction.VERTICAL
                )
                movePipe(
                    Pos(pipePos.x + Direction.DIAGONAL.movePos.x, pipePos.y + Direction.DIAGONAL.movePos.y),
                    Direction.DIAGONAL
                )
            }

            Direction.DIAGONAL -> {
                movePipe(
                    Pos(pipePos.x + Direction.HORIZONTAL.movePos.x, pipePos.y + Direction.HORIZONTAL.movePos.y),
                    Direction.HORIZONTAL
                )
                movePipe(
                    Pos(pipePos.x + Direction.VERTICAL.movePos.x, pipePos.y + Direction.VERTICAL.movePos.y),
                    Direction.VERTICAL
                )
                movePipe(
                    Pos(pipePos.x + Direction.DIAGONAL.movePos.x, pipePos.y + Direction.DIAGONAL.movePos.y),
                    Direction.DIAGONAL
                )
            }
        }
    }
}

fun main() {
    `파이프 옮기기 1`().solve()
}