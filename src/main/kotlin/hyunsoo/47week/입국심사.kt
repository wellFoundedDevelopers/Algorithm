package hyunsoo.`47week`

/**
 *
 * <문제>
 * [입국심사](https://school.programmers.co.kr/learn/courses/30/lessons/43238)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_입국심사` {

    fun solution(n: Int, times: IntArray): Long {

        val sortedTimes = times.sortedBy { it }

        var answer: Long = 0

        var left = sortedTimes.first().toLong()
        var right = sortedTimes.last().toLong() * n

        while (left <= right) {

            val mid = (left + right) / 2
            var people: Long = 0

            sortedTimes.forEach { time ->
                people += mid / time
            }

            if (n <= people) {
                answer = mid
                right = mid - 1
            } else {
                left = mid + 1
            }

        }

        return answer
    }
}

fun main() {
    전현수_입국심사()
        .solution(6, intArrayOf(7, 10))
        .apply {
            println(this)
        }
}