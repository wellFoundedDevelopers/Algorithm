package hyunsoo.`55week`

/**
 *
 * <문제>
 * [컵홀더](https://www.acmicpc.net/problem/2810)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_컵홀더` {

    fun solution() {

        val cnt = readln().toInt()
        val seatInfo = readln()

        var wasL = false

        var answer = 0
        var existCouple = false
        for (index in seatInfo.indices) {

            if (wasL) {
                wasL = false
                continue
            }
            when (seatInfo[index]) {
                'S' -> {
                    answer++
                }

                'L' -> {
                    answer++
                    wasL = true
                    existCouple = true
                }
            }
        }

        if (existCouple) answer++

        println(answer)
    }
}

fun main() {
    전현수_컵홀더().solution()
}