package byeonghee.week57

import java.util.PriorityQueue

class `소병희_귤 고르기` {
    fun solution(k: Int, tangerine: IntArray): Int {
        val hm = HashMap<Int, Int>()
        val pq = PriorityQueue<Int> { a, b -> b - a }
        var rest = k
        var answer = 0

        for(t in tangerine) {
            if (hm.contains(t).not()) hm[t] = 1
            else hm[t] = hm[t]!! + 1
        }

        pq.addAll(hm.values)
        while(pq.isNotEmpty() && rest > 0) {
            rest -= pq.poll()
            answer++
        }

        return answer
    }
}