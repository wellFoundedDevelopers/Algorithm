package hyunsoo.`49week`

import java.util.*
import kotlin.math.min

/**
 *
 * <문제>
 * [빗물](https://www.acmicpc.net/problem/14719)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_빗물` {

    fun solution() {

        val (h, w) = readln().split(" ").map { it.toInt() }
        val heightInfo = readln().split(" ").map { it.toInt() }
        var amount = 0

        for (i in 1 until w) {

            val leftMax = heightInfo.subList(i, heightInfo.size).maxOf { it }
            val rightMax = heightInfo.subList(0, i + 1).maxOf { it }

            val minHeight = min(leftMax, rightMax)

            if (heightInfo[i] < minHeight) amount += minHeight - heightInfo[i]
        }


        println(amount)
    }
}

fun main() {
    전현수_빗물().solution()
}