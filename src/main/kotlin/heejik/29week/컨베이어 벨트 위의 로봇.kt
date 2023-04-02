package heejik.`29week`

import java.util.*
import kotlin.properties.Delegates


class `컨베이어 벨트 위의 로봇` {

    private var n by Delegates.notNull<Int>()
    private var k by Delegates.notNull<Int>()
    private var inPosition by Delegates.notNull<Int>()
    private var outPosition by Delegates.notNull<Int>()
    private lateinit var belt: MutableList<Int>
    private val robots = mutableListOf<Robot>()

    inner class Robot(
        var position: Int,
    ) {
        fun move() {
            val movePosition = (position + 1) % (2 * n)
            if (movePosition == outPosition) {
                position = -1
                return
            }

            position = movePosition
        }

        override fun toString(): String {
            return this.position.toString()
        }
    }

    fun solve() {
        setting()
        operate()
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            k = this[1]
        }
        inPosition = 0
        outPosition = n - 1
        belt = readln().split(' ').map { it.toInt() }.toMutableList()
    }


    private fun operate() {
        var cnt = 0
        while (true) {
            cnt++
            rotateBelt()
            moveRobotInOrder()
            addRobot()
            if (isEnd()) break
        }
        println(cnt)
    }

    private fun rotateBelt() {
        Collections.rotate(belt, 1)
        moveRobot()
    }

    private fun moveRobot() {
        robots.forEach {
            it.move()
        }
        robots.removeIf { it.position == -1 }
    }

    private fun moveRobotInOrder() {
        robots.forEach { robot ->
            val movePosition = (robot.position + 1) % (2 * n)

            if (robots.any { it.position == movePosition }) return@forEach
            if (belt[movePosition] == 0) return@forEach
            belt[movePosition]--
            if (movePosition == outPosition) {
                robot.position = -1
                return@forEach
            }
            robot.position = movePosition
        }
        robots.removeIf { it.position == -1 }
    }

    private fun addRobot() {
        if (belt[inPosition] != 0) {
            robots.add(Robot(position = 0))
            belt[inPosition]--
        }
    }

    private fun isEnd() = belt.count { it == 0 } >= k
}

fun main() {
    `컨베이어 벨트 위의 로봇`().solve()

}