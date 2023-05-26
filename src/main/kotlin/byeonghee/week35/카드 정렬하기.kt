package byeonghee.week35

import java.io.*
import java.util.PriorityQueue

class 소병희_카드정렬하기 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val pq = PriorityQueue<Long>()
            var answer = 0L
            var sum = 0L

            for(i in 0 until n) {
                pq.add(readLine().toLong())
            }

            while(pq.size > 1) {
                sum = pq.poll() + pq.poll()
                answer += sum
                pq.add(sum)
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_카드정렬하기.solve()
}