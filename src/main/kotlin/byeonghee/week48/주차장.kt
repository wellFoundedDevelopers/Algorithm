package byeonghee.week48

import java.util.PriorityQueue
import kotlin.collections.ArrayDeque

class 소병희_주차장 {

    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt()}
            val fee = IntArray(n)
            val weight = IntArray(m + 1)
            val parked = IntArray(m + 1) { -1 }
            val spaceQ = PriorityQueue<Int>()
            val waitQ = ArrayDeque<Int>()
            var ans = 0

            repeat(n) { i -> fee[i] = readLine().toInt() }
            repeat(m) { i -> weight[i+1] = readLine().toInt() }

            spaceQ.addAll(0 until n)
            repeat(2*m) {
                val car = readLine().toInt()

                if (car < 0) {
                    val space = parked[car * -1]
                    parked[car * -1] = -1
                    spaceQ.add(space)
                    ans += fee[space] * weight[car * -1]
                }
                else waitQ.add(car)

                if (spaceQ.isNotEmpty() && waitQ.isNotEmpty()) {
                    val space = spaceQ.poll()
                    val car = waitQ.removeFirst()

                    parked[car] = space
                }
            }

            println(ans)
        }
    }
}