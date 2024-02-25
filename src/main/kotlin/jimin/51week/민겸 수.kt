package jimin.`51week`

import kotlin.math.*

class `민겸 수` {
    fun solve() {
        val maxResult = StringBuilder()
        val minResult = StringBuilder()

        val mgValue = readln()

        var firstIdx = 0
        for (i in mgValue.indices) {
            if (mgValue[i] == 'K') {
                changeToDecimal(firstIdx, i, mgValue, maxResult)
                changeToDecimal(firstIdx, i - 1, mgValue, minResult)
                minResult.append(5)
                firstIdx = i + 1
            }
        }

        for(i in firstIdx until mgValue.length) {
            maxResult.append(1)
        }
        changeToDecimal(firstIdx, mgValue.length - 1, mgValue, minResult)

        println(maxResult)
        println(minResult)
    }

    private fun changeToDecimal(first: Int, last: Int, mgValue: String, maxResult: StringBuilder) {
        if(last < first) {
            return
        }

        if (mgValue[last] == 'K') {
            maxResult.append(5)
        } else {
            maxResult.append(1)
        }
        repeat(last - first) {
            maxResult.append(0)
        }
    }
}

fun main() {
    `민겸 수`().solve()
}