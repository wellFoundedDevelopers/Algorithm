package heejik.`31week`

import kotlin.math.min
import kotlin.properties.Delegates

class 빗물 {

    var h by Delegates.notNull<Int>()
    var w by Delegates.notNull<Int>()
    private lateinit var blocks: List<Int>
    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            h = this[0]
            w = this[1]
        }
        blocks = readln().split(' ').map { it.toInt() }
        println(getTotalMountOfRainwater())
    }

    private fun getTotalMountOfRainwater(): Int {
        var s = 0
        blocks.forEachIndexed { index, height ->
            val leftMax = blocks.filterIndexed { subIndex, i ->
                subIndex < index
            }.maxOrNull() ?: 0
            val rightMax = blocks.filterIndexed { subIndex, i ->
                subIndex > index
            }.maxOrNull() ?: 0
            (min(leftMax, rightMax) - height).run {
                if (this > 0) s += this
            }
        }
        return s
    }
}

fun main() {
    빗물().solve()
}