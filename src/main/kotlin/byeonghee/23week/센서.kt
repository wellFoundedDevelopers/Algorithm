package byeonghee.`23week`

import java.io.*
import java.util.*

class 소병희_센서 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val k = readLine().toInt()
            val sensors = readLine().split(" ").map { it.toInt() }.sorted()
            var answer = sensors.last() - sensors.first()

            if (n == 1) {
                println(0)
                return
            }

            val pq =  PriorityQueue<Int>(n - 1, Collections.reverseOrder())

            for(i in 1 until n) {
                pq.add(sensors[i] - sensors[i - 1])
            }

            for(i in 0 until k - 1) {
                if (pq.isEmpty()) break
                answer -= pq.poll()
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_센서.solve()
}