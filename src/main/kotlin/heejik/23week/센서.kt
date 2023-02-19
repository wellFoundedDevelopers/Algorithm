package heejik.`23week`

import java.util.PriorityQueue

class 센서 {
    fun solve() {
        var answer = 0
        val n = readln().toInt()
        val k = readln().toInt()
        val sensors = readln().split(' ').map { it.toInt() }.sorted()
        val pq = PriorityQueue<Pair<Int, Int>> { a, b ->    // a: idx, b: 차이
            b.second - a.second
        }

        for (i in 0 until n - 1) {
            pq.add(Pair(i, sensors[i + 1] - sensors[i]))
        }

        val splits = mutableListOf<Int>()
        repeat(k - 1) {
            splits.add((pq.poll() ?: return@repeat).first)
        }

        var preIdx = 0

        splits.sorted().forEach {
            answer += sensors[it] - sensors[preIdx]
            preIdx = it + 1
        }
        answer += sensors[n - 1] - sensors[preIdx]

        println(answer)
    }
}


fun main() {
    센서().solve()
}