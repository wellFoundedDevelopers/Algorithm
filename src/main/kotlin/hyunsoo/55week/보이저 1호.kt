package hyunsoo.`55week`

/**
 *
 * <문제>
 * [보이저 1호](https://www.acmicpc.net/problem/3987)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_보이저_1호` {

    private data class Position(val x: Int, val y: Int)

    private enum class Directions(val keyword: String, val dir: Position) {
        Up("U", Position(-1, 0)),
        Right("R", Position(0, 1)),
        Down("D", Position(1, 0)),
        Left("L", Position(0, -1)),
    }

    private val board = mutableListOf<MutableList<String>>()

    fun solution() {

        val (n, m) = readln().split(" ").map { it.toInt() }

        var answerDir = ""
        var answer = -1

        repeat(n) {
            val row = readln().chunked(1) as MutableList
            board.add(row)
        }

        val voyagerPos = readln()
            .split(" ")
            .map {
                it.toInt()
            }.run {
                Position(this[0] - 1, this[1] - 1)
            }

        Directions.values().forEach { directionInfo ->

            var curPos = voyagerPos
            var curDirectionInfo = directionInfo
            var time = 1

            while (true) {

                val nx = curPos.x + curDirectionInfo.dir.x
                val ny = curPos.y + curDirectionInfo.dir.y

                if (nx !in 0 until n ||
                    ny !in 0 until m ||
                    board[nx][ny] == BLACK_HOLE
                ) {
                    if (answer < time) {
                        answer = time
                        answerDir = directionInfo.keyword
                    }
                    break
                }

                time++

                if (n * m * 2 < time) {
                    println(directionInfo.keyword)
                    println("Voyager")
                    return
                }

                when (board[nx][ny]) {
                    LEFT_UP_OR_RIGHT_DOWN -> {
                        when (curDirectionInfo) {
                            Directions.Up -> curDirectionInfo = Directions.Right
                            Directions.Left -> curDirectionInfo = Directions.Down
                            Directions.Right -> curDirectionInfo = Directions.Up
                            Directions.Down -> curDirectionInfo = Directions.Left
                        }
                    }

                    RIGHT_UP_OR_LEFT_DOWN -> {
                        when (curDirectionInfo) {
                            Directions.Up -> curDirectionInfo = Directions.Left
                            Directions.Left -> curDirectionInfo = Directions.Up
                            Directions.Right -> curDirectionInfo = Directions.Down
                            Directions.Down -> curDirectionInfo = Directions.Right
                        }
                    }
                }
                curPos = Position(nx, ny)

            }
        }


        println(answerDir)
        println(answer)
    }

    companion object {
        const val LEFT_UP_OR_RIGHT_DOWN = "/"
        const val RIGHT_UP_OR_LEFT_DOWN = "\\"
        const val BLACK_HOLE = "C"
        const val EMPTY = "."
    }
}

fun main() {
    전현수_보이저_1호().solution()
}