package heejik.`23week`

import kotlin.properties.Delegates

class `로봇 청소기` {

    private var cleanedCnt = 0

    data class Pos(
        var x: Int,
        var y: Int,
    ) {
        operator fun plus(other: Pos) = Pos(x = x + other.x, y = y + other.y)
        operator fun contains(other: Pos) = other.x in 0 until x && other.y in 0 until y
    }

    enum class Direction(val goPos: Pos, val backPos: Pos) {
        NORTH(goPos = Pos(-1, 0), backPos = Pos(1, 0)),
        EAST(goPos = Pos(0, 1), backPos = Pos(0, -1)),
        SOUTH(goPos = Pos(1, 0), backPos = Pos(-1, 0)),
        WEST(goPos = Pos(0, -1), backPos = Pos(0, 1)),
    }

    enum class Command {
        GO, BACK
    }

    data class RobotCleaner(
        val pos: Pos,
        var direction: Direction,
    ) {
        fun go() {
            pos.x += direction.goPos.x
            pos.y += direction.goPos.y
        }

        fun back() {
            pos.x += direction.backPos.x
            pos.y += direction.backPos.y
        }

        fun rotate() {
            direction = Direction.values()[(direction.ordinal - 1 + 4) % 4]
        }
    }

    private fun RobotCleaner.canMove(command: Command): Boolean {
        val nPos = when (command) {
            Command.GO -> direction.goPos + pos
            Command.BACK -> direction.backPos + pos
        }
        return nPos in Pos(n, m) && room[nPos.x][nPos.y] != 1
    }

    private fun RobotCleaner.clean(pos: Pos) {
        if (room[pos.x][pos.y] == 0) {
            room[pos.x][pos.y] = Int.MAX_VALUE
            cleanedCnt++
        }
    }

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()

    private lateinit var robotCleaner: RobotCleaner
    private val room = mutableListOf<MutableList<Int>>()

    fun solve() {
        setting()
        operate()

        println(cleanedCnt)
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = first()
            m = last()
        }
        val (r, c, d) = readln().split(' ').map { it.toInt() }
        robotCleaner = RobotCleaner(pos = Pos(r, c), direction = Direction.values()[d])

        repeat(n) {
            room.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }
    }

    private fun operate() {
        while (true) {
            val robotPos = robotCleaner.pos

            // 1
            robotCleaner.clean(robotPos)

            if (Direction.values().any {
                    val nPos = robotPos + it.goPos
                    nPos in Pos(n, m) && room[nPos.x][nPos.y] == 0
                }) {

                // 3
                robotCleaner.rotate()

                val nPos = robotPos + robotCleaner.direction.goPos
                if (robotCleaner.canMove(Command.GO) && room[nPos.x][nPos.y] == 0) {
                    robotCleaner.go()
                }

            } else {
                // 2
                if (robotCleaner.canMove(Command.BACK)) {
                    robotCleaner.back()
                } else {
                    break
                }
            }
        }
    }
}

fun main() {
    `로봇 청소기`().solve()
}