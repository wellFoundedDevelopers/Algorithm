package hyunsoo.`19week`

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.absoluteValue
import kotlin.math.round

/**
 *
 * <문제>
 * [통계학](https://www.acmicpc.net/problem/2108)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 * 정수 / 정수를 해서 소숫점 자리들이 나오지 않고 몫만 딱 떨어졌음.
 */
class `전현수_통계학` {

    fun solution() {
        val numCnt = readln().toInt()
        val numList = mutableListOf<Int>()
        val counterMap = mutableMapOf<Int, Int>()

        repeat(numCnt) {
            numList.add(readln().toInt())
        }

        numList.sort()

        val arithmeticMean = DecimalFormat("0").run {
            roundingMode = RoundingMode.HALF_UP
            val arithmeticMean = format(numList.sum().toDouble() / numCnt)
            if (arithmeticMean == "-0") "0" else arithmeticMean
        }

        println(arithmeticMean)

        println(numList[numList.size / 2])

        numList.forEach { num ->
            counterMap[num] = counterMap.getOrDefault(num, 0) + 1
        }

        counterMap.toList()
            .sortedByDescending { it.second }
            .apply {
                if (this.size > 1 &&
                    this.first().second == this[1].second
                ) {
                    println(this[1].first)
                } else {
                    println(this.first().first)
                }
            }

        println(numList.last() - numList.first())

    }
}

fun main() {
    전현수_통계학().solution()
}