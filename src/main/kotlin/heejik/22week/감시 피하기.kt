package heejik.`22week`

import kotlin.properties.Delegates
import kotlin.system.exitProcess

class `감시 피하기` {

    private val hall = mutableListOf<MutableList<String>>()
    var n by Delegates.notNull<Int>()

    companion object {
        private const val STUDENT = "S"
        private const val TEACHER = "T"
        private const val OBJECT = "O"
        private const val BLANK = "X"
        private const val MAX_OBJECT_COUNT = 3
    }

    enum class Move(val pos: Pos) {
        TOP(pos = Pos(x = -1, y = 0)),
        BOTTOM(pos = Pos(x = +1, y = 0)),
        LEFT(pos = Pos(x = 0, y = -1)),
        RIGHT(pos = Pos(x = 0, y = +1))
    }

    data class Pos(
        val x: Int,
        val y: Int
    ) {
        operator fun plus(other: Pos) = Pos(x = this.x + other.x, y = this.y + other.y)
        infix fun isInHall(hallSize: Int) = x in 0 until hallSize && y in 0 until hallSize
    }


    fun solve() {
        setting()
        start()
    }

    private fun setting() {
        n = readln().toInt()
        repeat(n) {
            hall.add(readln().split(' ').toMutableList())
        }
    }

    private fun start() {
        pick(selectedObject = listOf())
        print("NO")
    }

    private fun pick(selectedObject: List<Pos>) {
        if (selectedObject.size == MAX_OBJECT_COUNT) {
            if (check()) {
                println("YES")
                exitProcess(0)
            }
            return
        }

        repeat(n) { x ->
            repeat(n) { y ->
                if (hall[x][y] == BLANK) {
                    hall[x][y] = OBJECT
                    pick(selectedObject.plus(Pos(x, y)))
                    hall[x][y] = BLANK
                }
            }
        }
    }

    private fun check(): Boolean {
        hall.forEachIndexed { x, row ->
            row.forEachIndexed row@{ y, s ->
                if (s != TEACHER) return@row

                if (checkByTeacher(teacherPos = Pos(x, y)).not()) return false
            }
        }
        return true
    }

    private fun checkByTeacher(teacherPos: Pos): Boolean {

        if (checkByTeacherPosWithMove(pos = teacherPos, move = Move.TOP).not()) return false

        if (checkByTeacherPosWithMove(pos = teacherPos, move = Move.BOTTOM).not()) return false

        if (checkByTeacherPosWithMove(pos = teacherPos, move = Move.LEFT).not()) return false

        if (checkByTeacherPosWithMove(pos = teacherPos, move = Move.RIGHT).not()) return false

        return true
    }

    private fun checkByTeacherPosWithMove(pos: Pos, move: Move): Boolean {
        var newPos = pos + move.pos
        while (newPos isInHall n) {
            when (hall[newPos.x][newPos.y]) {
                OBJECT -> break
                STUDENT -> return false
            }
            newPos += move.pos
        }
        return true
    }
}

fun main() {
    `감시 피하기`().solve()
}