package byeonghee.`23week`

import java.io.*

class 소병희_보물섬 {

    companion object {
        data class Trip(val r: Int, val c: Int, val dist: Int)

        val dr = arrayOf(-1, 0, 1, 0)
        val dc = arrayOf(0, 1, 0, -1)

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (h, w) = readLine().split(" ").map { it.toInt() }
            val treasureMap = Array(h + 2) { CharArray(w + 2) { 'W' } }
            val queue = ArrayDeque<Trip>()
            var answer = 0
            var trip: Trip
            var nxtR = 0
            var nxtC = 0

            for(r in 1 until h + 1) {
                readLine().forEachIndexed { c, v ->
                    treasureMap[r][c + 1] = v
                }
            }

            for(r in 1 until h + 2) for(c in 1 until w + 2) {
                if (treasureMap[r][c] == 'W') continue
                val visited = Array(h + 2) { BooleanArray(w + 2) }
                queue.add(Trip(r, c, 0))

                while(queue.isNotEmpty()) {
                    trip = queue.removeFirst()
                    if (visited[trip.r][trip.c]) continue

                    answer = maxOf(answer, trip.dist)
                    visited[trip.r][trip.c] = true

                    for(d in 0 until 4) {
                        nxtR = trip.r + dr[d]
                        nxtC = trip.c + dc[d]
                        if (treasureMap[nxtR][nxtC] == 'W') continue

                        queue.add(Trip(nxtR, nxtC, trip.dist + 1))
                    }
                }
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_보물섬.solve()
}