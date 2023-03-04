package heejik.`25week`

import java.util.Collections
import kotlin.math.pow

class 톱니바퀴 {

    data class Gear(
        val number: Int, var state: MutableList<Int> = mutableListOf()
    ) {
        fun rotate(rotation: Rotation): Rotation {
            Collections.rotate(this.state, rotation.distance)
            return rotation
        }

        fun reverseRotate(rotation: Rotation): Rotation {
            Collections.rotate(this.state, rotation.getOpposition().distance)
            return rotation.getOpposition()
        }

        fun isLeftGearRotate(gear: Gear): Boolean {
            return this.state[6] != gear.state[2]
        }

        fun isRightGearRotate(gear: Gear): Boolean {
            return this.state[2] != gear.state[6]
        }
    }

    enum class Rotation(val distance: Int) {
        CLOCK(distance = 1), REVERSE_CLOCK(distance = -1);

        fun getOpposition() = if (this == CLOCK) REVERSE_CLOCK else CLOCK
    }

    private val gear1: Gear = Gear(number = 1)
    private val gear2: Gear = Gear(number = 2)
    private val gear3: Gear = Gear(number = 3)
    private val gear4: Gear = Gear(number = 4)
    private val gears = listOf(gear1, gear2, gear3, gear4)
    private fun getGearState() = readln().chunked(1).map { it.toInt() }.toMutableList()

    fun solve() {
        setting()
        getWayOfRotation()
        printScore()
    }

    private fun setting() {
        gears.forEach { gear ->
            gear.state = getGearState()
        }
    }

    private fun getWayOfRotation() {
        repeat(readln().toInt()) {
            val (number, rotationDistance) = readln().split(' ').map { it.toInt() }
            val rotation = if (rotationDistance == 1) Rotation.CLOCK else Rotation.REVERSE_CLOCK
            operate(number, rotation)
        }
    }

    private fun operate(number: Int, startRotation: Rotation) {
        var rotation = startRotation
        when (number) {
            1 -> {
                var isRight = gear1.isRightGearRotate(gear2)

                rotation = gear1.rotate(rotation)

                if (isRight.not()) return
                isRight = gear2.isRightGearRotate(gear3)
                rotation = gear2.reverseRotate(rotation)

                if (isRight.not()) return
                isRight = gear3.isRightGearRotate(gear4)
                rotation = gear3.reverseRotate(rotation)

                if (isRight.not()) return
                gear4.reverseRotate(rotation)
            }

            2 -> {
                val isLeft = gear2.isLeftGearRotate(gear1)
                var isRight = gear2.isRightGearRotate(gear3)

                rotation = gear2.rotate(rotation)
                if (isLeft) gear1.reverseRotate(rotation)

                if (isRight.not()) return
                isRight = gear3.isRightGearRotate(gear4)
                rotation = gear3.reverseRotate(rotation)

                if (isRight.not()) return
                gear4.reverseRotate(rotation)
            }

            3 -> {
                val isRight = gear3.isRightGearRotate(gear4)
                var isLeft = gear3.isLeftGearRotate(gear2)

                rotation = gear3.rotate(rotation)
                if (isRight) gear4.reverseRotate(rotation)

                if (isLeft.not()) return
                isLeft = gear2.isLeftGearRotate(gear1)
                rotation = gear2.reverseRotate(rotation)

                if (isLeft.not()) return
                gear1.reverseRotate(rotation)
            }

            4 -> {
                var isLeft = gear4.isLeftGearRotate(gear3)

                rotation = gear4.rotate(rotation)

                if (isLeft.not()) return
                isLeft = gear3.isLeftGearRotate(gear2)
                rotation = gear3.reverseRotate(rotation)

                if (isLeft.not()) return
                isLeft = gear2.isLeftGearRotate(gear1)
                rotation = gear2.reverseRotate(rotation)

                if (isLeft.not()) return
                gear1.reverseRotate(rotation)
            }
        }
    }

    private fun printScore() {
        var score = 0
        gears.forEach { gear ->
            if (gear.state[0] == 1) score += 2.0.pow(gear.number - 1).toInt()
        }
        println(score)
    }
}

fun main() {
    톱니바퀴().solve()
}