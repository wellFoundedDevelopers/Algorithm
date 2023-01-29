package byeonghee.`20week`

import java.io.*
import java.lang.Integer.max

class 소병희_안전영역 {

    companion object {
        lateinit var area : Array<List<Int>>
        lateinit var visited : Array<BooleanArray>
        var n = 0

        val mv = listOf(
            Pair(-1, 0),
            Pair(0, 1),
            Pair(1, 0),
            Pair(0, -1)
        )

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            n = readLine().toInt()
            area = Array(n) { readLine().split(" ").map { it.toInt() }}
            val minRain = area.minOf { it.minOf { it }}
            val maxRain = area.maxOf { it.maxOf { it }}
            var answer = 1

            for (rain in minRain until maxRain) {
                visited = Array(n) { BooleanArray(n) }
                var count = 0
                for(i in 0 until n) for(j in 0 until n) {
                    if (visited[i][j] || area[i][j] <= rain) continue
                    dfs(i, j, rain)
                    count++
                }
                answer = max(answer, count)
            }

            println(answer)
        }

        fun dfs(r: Int, c: Int, rain: Int) {
            if (r !in 0 until n || c !in 0 until n) return
            if (area[r][c] <= rain || visited[r][c]) return
            visited[r][c] = true

            for((dr, dc) in mv) {
                dfs(r + dr, c + dc, rain)
            }
        }
    }
}

fun main() {
    소병희_안전영역.solve()
}