package heejik.`48week`

import java.util.PriorityQueue
import kotlin.properties.Delegates

class 백도어 {

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    private lateinit var roads: List<MutableList<Road>>

    data class Road(
        val destination: Int,
        val time: Long
    )

    fun solve() = with(System.`in`.bufferedReader()) {
        readLine().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        val cant = readLine().split(' ').map { it == "1" } as MutableList
        cant[n - 1] = false

        roads = List(n) { mutableListOf() }
        repeat(m) {
            val (a, b, t) = readLine().split(' ').map { it.toInt() }
            if (cant[a] || cant[b]) return@repeat   // in 으로 해서 문제였구나,,,
            roads[a].add(Road(b, t.toLong()))
            roads[b].add(Road(a, t.toLong()))
        }

        println(getMinTime(0))
    }


    fun getMinTime(start: Int): Long {
        val times = LongArray(n) { Long.MAX_VALUE }
        times[start] = 0L

        val pq = PriorityQueue<Road> { a1, a2 -> (a1.time - a2.time).toInt() }
        pq.add(Road(start, 0L))

        while (pq.isNotEmpty()) {
            val (current, time) = pq.poll()
            if (times[current] < time) continue

            roads[current].forEach {
                val destination = it.destination
                val newTime = time + it.time
                if (newTime < times[destination]) {
                    times[destination] = newTime
                    pq.add(Road(destination, newTime))
                }
            }
        }
        return if (times[n - 1] == Long.MAX_VALUE) -1 else times[n - 1]
    }
}

fun main() {
    백도어().solve()
}

