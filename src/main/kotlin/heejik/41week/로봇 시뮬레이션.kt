package heejik.`41week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.absoluteValue
import kotlin.properties.Delegates


class `로봇 시뮬레이션` {

    data class Pos(
        var x: Int,
        var y: Int
    ) {
        operator fun plus(other: Pos): Pos = Pos(x + other.x, y + other.y)
    }

    enum class Direction(val movePos: Pos) {
        W(Pos(0, -1)), N(Pos(-1, 0)), E(Pos(0, 1)), S(Pos(1, 0))
    }

    enum class Command {
        L, R, F
    }

    inner class Robot(
        val number: Int,
        var position: Pos,
        var direction: Direction
    ) {
        fun move(command: Command) {
            val directions = Direction.values()
            when (command) {
                Command.L -> {
                    direction = directions[(directions.indexOf(direction) - 1 + 4) % directions.size]
                }

                Command.R -> {
                    direction = directions[(directions.indexOf(direction) + 1) % directions.size]
                }

                Command.F -> {
                    position += direction.movePos
                }
            }

            if (position.x !in 0 until rowSize || position.y !in 0 until columnSize) {
                throw Exception("Robot $number crashes into the wall")
            }
            robots.filter { it.number != this.number }.forEach { other ->
                if (other.position.x == this.position.x && other.position.y == this.position.y) {
                    throw Exception("Robot $number crashes into robot ${other.number}")
                }
            }
        }
    }

    private var rowSize by Delegates.notNull<Int>()
    private var columnSize by Delegates.notNull<Int>()
    private var robotCount by Delegates.notNull<Int>()
    private var commandCount by Delegates.notNull<Int>()
    private val robots = mutableListOf<Robot>()

    fun solve() {
        setting()
    }

    private fun setting() = BufferedReader(InputStreamReader(System.`in`)).run {
        readLine().split(' ').map { it.toInt() }.run {
            columnSize = this[0]
            rowSize = this[1]
        }
        readLine().split(' ').map { it.toInt() }.run {
            robotCount = this[0]
            commandCount = this[1]
        }

        repeat(robotCount) {
            readLine().split(' ').run {
                val (x, y) = Pair((this[1].toInt() - rowSize).absoluteValue, this[0].toInt() - 1)
                val dir = Direction.valueOf(this[2])

                robots.add(Robot(it + 1, Pos(x, y), dir))
            }
        }

        repeat(commandCount) {
            readLine().split(' ').run {
                val (robotNumber, repeatCount) = Pair(this[0].toInt() - 1, this[2].toInt())
                val command = Command.valueOf(this[1])

                repeat(repeatCount) {
                    kotlin.runCatching {
                        robots[robotNumber].move(command)
                    }.onFailure {
                        println(it.message)
                        return
                    }
                }
            }
        }
        println("OK")
    }
}

fun main() {
    `로봇 시뮬레이션`().solve()
}