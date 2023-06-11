package byeonghee.week39

import java.io.StreamTokenizer

class 소병희_자유이용권 {

    companion object {
        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input() : Long {
                nextToken()
                return nval.toLong()
            }

            val n = input()
            var x = 0L
            var maxTime = 0L
            var other = 0L

            for(i in 0 until n) {
                x = input()
                if (x > maxTime) {
                    other += maxTime
                    maxTime = x
                }
                else other += x
            }

            println(if (maxTime > other + 1) other * 2 + 1 else maxTime + other)
        }
    }
}

fun main() {
    소병희_자유이용권.solve()
}