package byeonghee.week33

import java.io.*

class 소병희_물병 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, k) = readLine().split(" ").map { it.toInt() }
            val bits = ArrayDeque<Int>()

            var i = n
            var j = 0
            while(i > 0) {
                if (i % 2 == 1) bits.addFirst(j)
                j++
                i /= 2
            }

            if (bits.size <= k) {
                print(0)
                return
            }

            var bit = 0
            var answer = 0
            while(bits.size >= k) {
                bit = (1 shl bits.removeLast()).also { answer += it - bit } * 2
            }
            print(answer)
        }
    }
}

fun main() {
    소병희_물병.solve()
}