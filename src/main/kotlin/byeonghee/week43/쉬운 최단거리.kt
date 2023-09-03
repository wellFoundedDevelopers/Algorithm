package byeonghee.week43

import java.io.BufferedReader
import java.io.InputStreamReader

class 소병희_쉬운최단거리 {

    companion object {
        val dr = intArrayOf(1, 0, -1, 0)
        val dc = intArrayOf(0, 1, 0, -1)

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val ground = Array(n) { IntArray(m) { -1 } }
            val sb = StringBuilder()

            val q = ArrayDeque<IntArray>()

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    if (v.toInt() == 2) {
                        q.add(intArrayOf(i, j, 0))
                        ground[i][j] = 0
                    }
                    else if (v.toInt() == 0) {
                        ground[i][j] = 0
                    }
                }
            }

            var nr = 0
            var nc = 0
            var r = 0
            var c = 0
            var v = 0

            while(q.isNotEmpty()) {
                q.removeFirst().let {
                    r = it[0]
                    c = it[1]
                    v = it[2]
                }

                for(d in 0 until 4) {
                    nr = r + dr[d]
                    nc = c + dc[d]
                    if (nr !in 0 until n || nc !in 0 until m) continue
                    if (ground[nr][nc] == 0) continue
                    if (ground[nr][nc] != -1) continue
                    ground[nr][nc] = v + 1
                    q.add(intArrayOf(nr, nc, v + 1))
                }
            }

            repeat(n) { i ->
                repeat(m) { j ->
                    sb.append("${ground[i][j]} ")
                }
                sb.appendLine()
            }

            print(sb)
        }
    }
}

fun main() {
    소병희_쉬운최단거리.solve()
}