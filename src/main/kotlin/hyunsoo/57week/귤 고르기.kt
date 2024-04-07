package hyunsoo.`57week`

/**
 *
 * <문제>
 * [귤 고르기](https://school.programmers.co.kr/learn/courses/30/lessons/138476)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_귤_고르기` {

    fun solution(k: Int, tangerine: IntArray): Int {

        val counter = mutableMapOf<Int, Int>()
        var targetCnt = k
        var answer: Int = 0

        // getCountMap
        tangerine.forEach {
            val pre = counter.getOrDefault(it, 0)
            counter[it] = pre + 1
        }

        val sortedCounter = counter.map {
            it.key to it.value
        }. sortedByDescending {
            it.second
        }

        for (index in sortedCounter.indices) {
            val (key, value) = sortedCounter[index]

            if (targetCnt <= value) {
                answer++
                break
            } else {
                targetCnt -= value
                answer++
            }
        }

        return answer
    }

    private fun IntArray.getCountMap(): Map<Int, Int> {
        return this.toList()
            .groupingBy { it }
            .eachCount()
    }
}

fun main() {
    전현수_귤_고르기().solution(
        6, intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)
    ).apply {
        println(this)
    }
}