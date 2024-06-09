package hyunsoo.`66week`

import java.util.*

/**
 *
 * <문제>
 * [순회강연](https://www.acmicpc.net/problem/2109)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_순회강연` {

    private data class Deal(val fee: Int, val day: Int)

    fun solution() {

        val cnt = readln().toInt()
        var ans = 0
        val dealList = PriorityQueue<Deal>(
            // -가 붙으면 내림차순
            compareBy { -it.fee }
        )

        var maxDay = 0

        repeat(cnt) {
            readln().split(" ").map { it.toInt() }.apply {
                dealList.add(Deal(this[0], this[1]))
                if (maxDay < this[1]) maxDay = this[1]
            }
        }

        val isOccupied = BooleanArray(maxDay + 1)

        while (dealList.isNotEmpty()) {

            val curDeal = dealList.poll()

            for (index in curDeal.day downTo 1) {

                // 차있지 않다면,
                if (isOccupied[index].not()) {
                    isOccupied[index] = true
                    ans += curDeal.fee
                    break
                }
            }

        }

        println(ans)
    }
}

fun main() {
    전현수_순회강연().solution()
}