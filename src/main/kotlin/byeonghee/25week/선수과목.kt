package byeonghee.`25week`

import java.io.StreamTokenizer
import java.util.PriorityQueue

class 소병희_선수과목 {

    companion object {
        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {

            fun input() : Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val m = input()

            val chain = IntArray(n + 1) { 1 }
            val pq = PriorityQueue(Comparator<Pair<Int, Int>> { a, b -> a.second - b.second })

            for(i in 0 until m) {
                pq.add(Pair(input(), input()))
            }

            while(pq.isNotEmpty()) {
                val (pre, course) = pq.poll()
                chain[course] = maxOf(chain[pre] + 1, chain[course])
            }

            val sb = StringBuilder()
            for(i in 1 .. n) {
                sb.append(chain[i])
                sb.append(' ')
            }
            println(sb)
        }
    }
}

fun main() {
    소병희_선수과목.solve()
}