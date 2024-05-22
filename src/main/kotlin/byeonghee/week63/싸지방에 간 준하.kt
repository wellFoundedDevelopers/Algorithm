package byeonghee.week63

import java.util.PriorityQueue

class `소병희_싸지방에 간 준하` {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val acc = IntArray(1_000_000)
            val pq = PriorityQueue<Int>()
            val mapping = IntArray(n+1)
            val answer = mutableListOf<Int>()
            var pc = 0

            for(i in 1 .. n) {
                val (s, e) = readLine().split(" ").map { it.toInt() }
                acc[s] += i
                acc[e] -= i
            }

            for(t in 0 until 1_000_000) {
                if (acc[t] > 0) {
                    if (pq.isEmpty()) {
                        mapping[acc[t]] = pc++
                        answer.add(1)
                    }
                    else {
                        mapping[acc[t]] = pq.poll().also { answer[it]++ }
                    }
                }
                else if (acc[t] < 0) {
                    pq.add(mapping[-1 * acc[t]])
                }
            }

            println(answer.size)
            println(answer.joinToString(" "))
        }
    }
}

fun main() {
    `소병희_싸지방에 간 준하`.solve()
}