package hyunsoo.`33week`

import kotlin.math.min

/**
 *
 * <문제>
 * [운동](https://www.acmicpc.net/problem/1956)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 * 범위 설정...
 *
 */
class `전현수_운동` {

    private lateinit var distanceTable: Array<IntArray>

    fun solution() {

        var ans = MAX_DISTANCE
        val (v, e) = readln().split(" ").map { it.toInt() }

        distanceTable = Array(v + 1) {
            IntArray(v + 1) { MAX_DISTANCE }
        }

        repeat(e) {
            val (start, end, value) = readln().split(" ").map { it.toInt() }
            distanceTable[start][end] = value
        }

        for (target in 1..v) {
            for (start in 1..v) {
                for (end in 1..v) {
                    distanceTable[start][end] =
                        min(
                            distanceTable[start][end],
                            distanceTable[start][target] + distanceTable[target][end]
                        )

                    if (start == end) continue

                    ans = min(ans, distanceTable[start][end] + distanceTable[end][start])
                }
            }
        }

        println(if (ans == MAX_DISTANCE) -1 else ans)

    }

    companion object {
        private const val MAX_DISTANCE = 99999999
    }
}

fun main() {
    전현수_운동().solution()
}