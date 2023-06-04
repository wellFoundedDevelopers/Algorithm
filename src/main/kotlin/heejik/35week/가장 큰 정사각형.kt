package heejik.`35week`

import kotlin.math.min
import kotlin.properties.Delegates

class `가장 큰 정사각형` {

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()

    val field = mutableListOf<MutableList<Int>>()

    fun solve() {
        setting()
        for (x in 1 until n) {
            for (y in 1 until m) {
                if (field[x][y] == 0) continue
                val minValue = min(min(field[x - 1][y - 1], field[x - 1][y]), field[x][y - 1])
                if (minValue == 0) continue
                field[x][y] = minValue + 1
            }
        }

        val maxValue = field.maxOf { it.max() }
        println(maxValue * maxValue)
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }

        repeat(n) {
            val row = readln().toList().map { it.digitToInt() }.toMutableList()
            field.add(row)
        }
    }
}

fun main() {
    `가장 큰 정사각형`().solve()
}