package hyunsoo.`27week`

/**
 *
 * <문제>
 * [주사위 굴리기](https://www.acmicpc.net/problem/14499)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_주사위_굴리기` {

    private data class Position(val x: Int, val y: Int)

    // 동 서 북 남
    private enum class DIR(val dir: Position, val command: Int) {
        EAST(Position(0, 1), 1),
        WEST(Position(0, -1), 2),
        NORTH(Position(-1, 0), 3),
        SOUTH(Position(1, 0), 4)
    }

    private val map = mutableListOf<MutableList<Int>>()

    private inner class Dice(x: Int, y: Int) {

        private var dice = IntArray(6)

        private var position = Position(x, y)

        fun move(dir: Position) {

            val nx = position.x + dir.x
            val ny = position.y + dir.y

            if (nx !in 0 until map.size || ny !in 0 until map.first().size) return

            position = Position(nx, ny)

            when (dir) {
                DIR.EAST.dir -> {
                    dice = intArrayOf(dice[2], dice[1], dice[5], dice[0], dice[4], dice[3])
                }

                DIR.WEST.dir -> {
                    dice = intArrayOf(dice[3], dice[1], dice[0], dice[5], dice[4], dice[2])
                }

                DIR.SOUTH.dir -> {
                    dice = intArrayOf(dice[4], dice[0], dice[2], dice[3], dice[5], dice[1])
                }

                DIR.NORTH.dir -> {
                    dice = intArrayOf(dice[1], dice[5], dice[2], dice[4], dice[0], dice[4])
                }
            }

            if (map[nx][ny] == 0) {
                map[nx][ny] = dice[BOTTOM]
            } else {
                dice[BOTTOM] = map[nx][ny]
                map[nx][ny] = 0
            }

            printTop()
        }

        private fun printTop() {
            println(dice[TOP])
        }

    }

    fun solution() {
        val (n, m, x, y, commandCnt) = readln().split(" ").map { it.toInt() }

        repeat(n) {
            val row = readln().split(" ").map { it.toInt() } as MutableList
            map.add(row)
        }

        val dice = Dice(x, y)

        val commands = readln().split(" ").map { it.toInt() }

        commands.forEach { command ->
            when (command) {
                DIR.EAST.command -> {
                    dice.move(DIR.EAST.dir)
                }

                DIR.WEST.command -> {
                    dice.move(DIR.WEST.dir)
                }

                DIR.NORTH.command -> {
                    dice.move(DIR.NORTH.dir)
                }

                DIR.SOUTH.command -> {
                    dice.move(DIR.SOUTH.dir)
                }
            }
        }
    }

    companion object {
        private const val TOP = 5
        private const val BOTTOM = 0
    }
}

fun main() {
    전현수_주사위_굴리기().solution()
}