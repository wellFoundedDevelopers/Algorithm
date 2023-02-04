package heejik.`21week`

import kotlin.math.max
import kotlin.properties.Delegates

class 스티커 {

    var n by Delegates.notNull<Int>()

    fun getTestCase() {
        repeat(readln().toInt()) {
            setting()
        }
    }

    fun setting() {
        n = readln().toInt()
        val sticker = mutableListOf<MutableList<Int>>()
        repeat(2) {
            val row = readln().split(' ').map { it.toInt() }.toMutableList()
            row.add(0,0)
            sticker.add(row)
        }

        solve(sticker)
    }

    fun solve(sticker: MutableList<MutableList<Int>>) {
        for (i in 2 until n + 1) {
            val firstCaseOfTop = sticker[1][i - 1]
            val secondCaseOfTop = sticker[1][i - 2]
            sticker[0][i] += max(firstCaseOfTop, secondCaseOfTop)

            val firstCaseOfDown = sticker[0][i - 1]
            val secondCaseOfDown = sticker[0][i - 2]
            sticker[1][i] += max(firstCaseOfDown, secondCaseOfDown)
        }

        println(sticker.maxOf { it.max() })
    }
}


fun main() {
    스티커().getTestCase()
}