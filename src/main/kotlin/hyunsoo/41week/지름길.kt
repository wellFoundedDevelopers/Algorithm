package hyunsoo.`41week`

import kotlin.math.min

/**
 *
 * <문제>
 * [지름길](https://www.acmicpc.net/problem/1446)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_지름길` {

    private data class ShortcutInfo(
        val start: Int, val end: Int, val cost: Int,
    )

    fun solution() {

        val (shortcutCnt, highwayLength) = readln().split(" ").map { it.toInt() }

        val shortcutInfoList = Array(shortcutCnt) {
            readln().split(" ")
                .map { it.toInt() }.run {
                    ShortcutInfo(first(), this[1], last())
                }
        }.filter {
            it.end <= highwayLength && it.cost < it.end - it.start
        }

        val dp = IntArray(10001) { it }

        for (end in 1..highwayLength) {
            dp[end] = dp[end - 1] + 1
            shortcutInfoList.forEach {
                if (end == it.end) dp[end] =
                    min(dp[end], dp[it.start] + it.cost)
            }
        }

        println(dp[highwayLength])
    }
}

fun main() {
    전현수_지름길().solution()
}