package byeonghee.`19week`

import java.io.*
import java.util.PriorityQueue

class 소병희_단지번호붙이기 {

    companion object {
        data class Pos(val r: Int, val c: Int)

        val mv = listOf(
            Pos(-1, 0),
            Pos(0, 1),
            Pos(1, 0),
            Pos(0, -1)
        )

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val complex = Array(n + 2) { IntArray(n + 2) }
            val visited = Array(n + 2) { BooleanArray(n + 2) }
            val q = ArrayDeque<Pos>()
            val answer = PriorityQueue(Comparator<Int> { a, b -> a - b })
            var count: Int

            repeat(n) { i ->
                readLine().mapIndexed { j, v -> complex[i + 1][j + 1] = v.digitToInt() }
            }

            for (i in 1 .. n) for (j in 1 .. n) {
                if (complex[i][j] == 0 || visited[i][j]) continue

                q.add(Pos(i, j))
                count = 0
                while (q.isNotEmpty()) {
                    val (r, c) = q.removeFirst()
                    if (complex[r][c] == 0 || visited[r][c] ) continue

                    visited[r][c] = true
                    for ((dr, dc) in mv) {
                        q.add(Pos(r + dr, c + dc))
                    }
                    count++
                }
                answer.add(count)
            }

            println(answer.size)
            while(answer.isNotEmpty()) println(answer.poll())
        }
    }
}

fun main() {
    소병희_단지번호붙이기.solve()
}