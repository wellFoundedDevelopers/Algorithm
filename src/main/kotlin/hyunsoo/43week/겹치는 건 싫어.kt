package hyunsoo.`43week`

import kotlin.math.max

/**
 *
 * <문제>
 * [겹치는 건 싫어](https://www.acmicpc.net/problem/20922)
 *
 * - 아이디어
 *
 * 와 계수 정렬...
 * - 트러블 슈팅
 *
 */
class `전현수_겹치는_건_싫어` {

    fun solution() {

        val (n, limit) = readln().split(" ").map { it.toInt() }
        val sequence = readln().split(" ").map { it.toInt() }

        var start = 0
        var end = 0
        var maxLength = 0

        val countChecker = IntArray(200000)
        countChecker[sequence[start]]++

        while (
            start in 0 until n &&
            end in 0 until n
        ) {
            val nextPointer = end + 1
            if (n <= nextPointer) break

            val nextNum = sequence[nextPointer]

            if (countChecker[nextNum] == limit) {
                countChecker[sequence[start]]--
                start++
                if (n <= start) break

            } else {
                countChecker[nextNum]++
                maxLength = max(nextPointer - start + 1, maxLength)
                end++
            }
        }

        println(maxLength)


    }
}

fun main() {
    전현수_겹치는_건_싫어().solution()
}