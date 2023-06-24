package hyunsoo.`41week`

import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [로봇 시뮬레이션](https://www.acmicpc.net/problem/2174)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_로봇_시물레이션` {

    private val robotList = mutableListOf<Robot>()
    private var width = 0
    private var height = 0

    private data class Position(val x: Int, val y: Int) {

        operator fun plus(other: Position) =
            Position(this.x + other.x, this.y + other.y)
    }

    private enum class DIRECTION(val position: Position) {
        N(Position(0, 1)),
        E(Position(1, 0)),
        S(Position(0, -1)),
        W(Position(-1, 0));

        fun turnLeft(): DIRECTION {
            return when (this) {
                N -> W
                W -> S
                S -> E
                E -> N
            }
        }

        fun turnRight(): DIRECTION {
            return when (this) {
                N -> E
                E -> S
                S -> W
                W -> N
            }
        }

        companion object {
            fun getByName(name: String) =
                requireNotNull(DIRECTION.values().find { it.name == name })
        }
    }

    private inner class Robot(
        x: Int, y: Int,
        var dir: DIRECTION,
        val name: Int,
    ) {

        private var _position = Position(x, y)
        val position
            get() = _position

        fun goForward() {

            val nextPosition = position + dir.position

            robotList
                .find { it.position == nextPosition }
                ?.let { targetRobot ->
                    crashWithRobot(targetRobot.name)
                }

            if (nextPosition.x !in 1..width ||
                nextPosition.y !in 1..height
            ) crashWithWall()

            _position = nextPosition
        }

        fun turnLeft() {
            dir = dir.turnLeft()
        }

        fun turnRight() {
            dir = dir.turnRight()
        }

        private fun crashWithWall() {
            println("Robot $name crashes into the wall")
            exitProcess(0)
        }

        private fun crashWithRobot(target: Int) {
            println("Robot $name crashes into robot $target")
            exitProcess(0)
        }

    }

    fun solution() {

        readln().split(" ").map { it.toInt() }.apply {
            width = first()
            height = last()
        }

        val (robotCnt, commandCnt) = readln().split(" ").map { it.toInt() }

        repeat(robotCnt) {
            val (x, y, dir) = readln().split(" ")
            robotList.add(
                Robot(x.toInt(), y.toInt(), DIRECTION.getByName(dir), it + 1)
            )
        }

        repeat(commandCnt) {

            val (stringName, command, cnt) = readln().split(" ")
            val name = stringName.toInt()
            val targetRobot = requireNotNull(robotList.find { it.name == name })

            repeat(cnt.toInt()) {
                when (command) {
                    "L" -> targetRobot.turnLeft()

                    "R" -> targetRobot.turnRight()

                    "F" -> targetRobot.goForward()
                }
            }
        }

        println("OK")
    }
}

fun main() {
    전현수_로봇_시물레이션().solution()
}