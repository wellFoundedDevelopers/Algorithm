package heejik.`61week`

import java.util.PriorityQueue

class `파일 합치기3` {

    fun solve() {
        val n = readln()
        val sizes = readln().split(' ').map { it.toLong() }
        val pq = PriorityQueue(sizes)
        var cost = 0L

        while (pq.size >= 2) {
            val small1 = pq.poll()
            val small2 = pq.poll()

            val sum = small1 + small2
            pq.add(sum)
            cost += sum
        }
        println(cost)
    }
}


fun main() {
    val t = readln().toInt()
    repeat(times = t) {
        `파일 합치기3`().solve()
    }
}