package heejik.`48week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.properties.Delegates

class 백도어 {

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    private lateinit var roads: MutableList<MutableList<Road>>

    data class Road(
        val destination: Int,
        val time: Int
    )

    fun solve() = with(BufferedReader(InputStreamReader(System.`in`))) {
        readLine().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        val cant = mutableListOf<Int>()
        readLine().split(' ').map { it.toInt() }.forEachIndexed { index, it ->
            if (it == 1 && index != n - 1) cant.add(index)
        }

        roads = MutableList(n) { mutableListOf() }
        repeat(m) {
            val (a, b, t) = readLine().split(' ').map { it.toInt() }
            if (a in cant || b in cant) return@repeat
            roads[a].add(Road(b, t))
            roads[b].add(Road(a, t))
        }


        println(getMinTime(0))
    }


    fun getMinTime(start: Int): Int {
        val times = MutableList(n) { Int.MAX_VALUE }
        times[start] = 0

        val deque = ArrayDeque<Road>()
        deque.add(Road(start, 0))

        while (deque.isNotEmpty()) {
            val (current, time) = deque.removeFirst()
            if (times[current] < time) continue

            roads[current].forEach {
                val destination = it.destination
                val newTime = time + it.time
                if (newTime < times[destination]) {
                    times[destination] = newTime
                    deque.add(Road(destination, newTime))
                }
            }
        }
        return if (times[n - 1] == Int.MAX_VALUE) -1 else times[n - 1]
    }
}

fun main() {
    백도어().solve()
}