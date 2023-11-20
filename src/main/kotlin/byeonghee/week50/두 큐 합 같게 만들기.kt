package byeonghee.week50

import kotlin.math.abs

class 소병희_두큐합같게만들기 {
    var answer = 0

    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val n = queue1.size
        var diff = 0L
        repeat(n) {
            diff += queue1[it] - queue2[it]
        }

        if (diff%2 == 1L) return -1

        val fullQ1 = queue1.plus(queue2)
        val fullQ2 = queue2.plus(queue1)

        var p1 = 0
        var p2 = 0
        while(diff != 0L && abs(p1 - p2) < n && (p1 + p2) < 3 * n) {
            if (diff > 0) {
                diff -= fullQ1[p1++ % (2 * n)] * 2
            }
            else {
                diff += fullQ2[p2++ % (2 * n)] * 2
            }
            answer++
        }

        return if (diff == 0L) answer else -1
    }
}