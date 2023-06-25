package hyunsoo.`39week`

import java.util.Collections
import java.util.PriorityQueue

/**
 *
 * <문제>
 * [자유 이용권](https://www.acmicpc.net/problem/25635)
 *
 * - 아이디어
 *
 *
 * - 트러블 슈팅
 * - Int 범위 초과
 */
class `전현수_자유_이용권` {

    fun solution() {

        val unUsedValue = readln().toLong()

        readln().split(" ").map { it.toLong() }.apply {
            val max = this.max()
            val otherSum = this.sum() - max

            if (max <= otherSum + 1) {
                println(otherSum + max)
            } else {
                println(otherSum * 2 + 1)
            }
        }
    }
}

fun main() {
    전현수_자유_이용권().solution()
}