package heejik.`50week`

class `두 큐 합 같게 만들기` {
    fun solution(_queue1: IntArray, _queue2: IntArray): Int {
        var count = 0
        val queue1 = ArrayDeque<Long>()
        val queue2 = ArrayDeque<Long>()

        queue1.addAll(_queue1.map { it.toLong() })
        queue2.addAll(_queue2.map { it.toLong() })
        var sumOfQueue1: Long = queue1.sum()
        var sumOfQueue2: Long = queue2.sum()

        while (queue1.isNotEmpty() && queue2.isNotEmpty()) {
            if (sumOfQueue1 > sumOfQueue2) {
                val removeNumber = queue1.removeFirst()
                queue2.addLast(removeNumber)
                sumOfQueue1 -= removeNumber
                sumOfQueue2 += removeNumber
            } else if (sumOfQueue1 < sumOfQueue2) {
                val removeNumber = queue2.removeFirst()
                queue1.addLast(removeNumber)
                sumOfQueue1 += removeNumber
                sumOfQueue2 -= removeNumber
            } else {
                return count
            }
            count++
            if (count > 600000) break
        }
        return -1
    }
}