package byeonghee.week34

import java.io.StreamTokenizer
import java.util.*

class 소병희_행복유치원 {

    companion object {
        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val k = input()
            val diffs = PriorityQueue<Int>(n-1, Collections.reverseOrder())
            var pre = input()
            var answer = 0


            for(i in 1 until n) {
                pre = input().also {
                    answer += it - pre
                    diffs.add(it - pre)
                }
            }

            repeat(k-1) {
                answer -= diffs.poll()
            }

            print(answer)
        }
    }
}

fun main() {
    소병희_행복유치원.solve()
}