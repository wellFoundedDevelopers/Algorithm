package byeonghee.week26

import java.io.*
import java.util.PriorityQueue
import kotlin.math.abs

class 소병희_절댓값힙 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val pq = PriorityQueue<Int> { a, b -> if (a + b == 0) a - b else abs(a) - abs(b) }

            var x : Int
            repeat(n) {
                x = readLine().toInt()
                when(x) {
                    0 -> println(pq.poll() ?: "0")
                    else -> pq.add(x)
                }
            }
        }
    }
}

fun main() {
    소병희_절댓값힙.solve()
}