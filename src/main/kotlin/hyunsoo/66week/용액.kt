package hyunsoo.`66week`

import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [용액](https://www.acmicpc.net/problem/2467)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_용액` {

    fun solution() {

        val cnt = readln().toInt()
        val array = readln().split(" ").map { it.toInt() }

        var startIndex = 0
        var endIndex = cnt - 1

        val answer = intArrayOf(array[startIndex], array[endIndex])
        var curNearest = array[startIndex] + array[endIndex]

        while (startIndex < endIndex) {

            val fir = array[startIndex]
            val sec = array[endIndex]

            val curSum = fir + sec

            // 절대 값이 작으면, 0에 더 가깝다는 뜻
            if (curSum.absoluteValue <= curNearest.absoluteValue) {
                curNearest = curSum
                answer[0] = fir
                answer[1] = sec
            }

            if (curSum < 0) startIndex++
            else endIndex--
        }

        println("${answer[0]} ${answer[1]}")
    }
}

fun main() {
    전현수_용액().solution()
}