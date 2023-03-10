package heejik.`26week`

import kotlin.math.abs

class `맥주 마시면서 걸어가기` {

    private fun String.toPos() : Pos{
        return this.split(' ').map { it.toInt() }.run {
            Pos(first(), last())
        }
    }

    data class Pos(
        val x: Int,
        val y: Int
    ) {
        operator fun minus(other: Pos): Int {
            return abs(x - other.x) + abs(y - other.y)
        }
    }

    fun getTestCaseCount() {
        val testCaseCount = readln().toInt()
        repeat(testCaseCount) {
            canGoFestival().run {
                println(if (this) "happy" else "sad")
            }
        }
    }

    fun canGoFestival(): Boolean {
        val n = readln().toInt()
        val conveniencePoses = mutableListOf<Pos>()
        var homePos = readln().toPos()

        repeat(n) {
            conveniencePoses.add(readln().toPos())
        }
        var festivalPos = readln().toPos()

        return start(homePos, festivalPos, conveniencePoses)
    }

    fun start(homePos: Pos, festivalPos: Pos, conveniencePoses: MutableList<Pos>): Boolean {
        val visited = MutableList(conveniencePoses.size) { false }
        val queue = ArrayDeque<Pos>()
        queue.add(homePos)

        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            if (pos - festivalPos <= 1000) {
                return true
            }
            conveniencePoses.forEachIndexed { index, conveniencePos ->
                if (pos - conveniencePos <= 1000 && visited[index].not()) {
                    queue.add(conveniencePos)
                    visited[index] = true
                }
            }
        }

        return false
    }
}

fun main() {
    `맥주 마시면서 걸어가기`().getTestCaseCount()
}