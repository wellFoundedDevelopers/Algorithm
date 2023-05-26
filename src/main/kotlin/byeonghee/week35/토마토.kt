package byeonghee.week35

import java.io.StreamTokenizer
import kotlin.system.exitProcess

class 소병희_토마토 {

    companion object {
        val dr = intArrayOf(-1, 0, 1, 0, 0, 0)
        val dc = intArrayOf(0, 1, 0, -1, 0, 0)
        val dz = intArrayOf(0, 0, 0, 0, 1, -1)

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val m = input()
            val n = input()
            val h = input()
            val box = Array(h) { Array(n) { IntArray(m) } }
            val visited = Array(h) { Array(n) { BooleanArray(m) } }
            val bfs = ArrayDeque<IntArray>()
            var answer = 0
            var tomatoes = 0
            var spaces = 0

            for(i in 0 until h) {
                for(j in 0 until n) {
                    for(k in 0 until m) {
                        box[i][j][k] = input()
                        when(box[i][j][k]) {
                            1 -> {
                                box[i][j][k] = 0
                                bfs.add(intArrayOf(j, k, i))
                                visited[i][j][k] = true
                                tomatoes++
                            }
                            -1 -> spaces++
                        }
                    }
                }
            }

            if (tomatoes == 0) {
                println(-1)
                exitProcess(0)
            }

            while(bfs.isNotEmpty()) {
                val (r, c, z) = bfs.removeFirst()

                for(d in 0 until 6) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    val nz = z + dz[d]
                    if (nr !in 0 until n || nc !in 0 until m || nz !in 0 until h) continue
                    if (visited[nz][nr][nc] || box[nz][nr][nc] == -1) continue
                    visited[nz][nr][nc] = true
                    box[nz][nr][nc] = box[z][r][c] + 1
                    bfs.add(intArrayOf(nr, nc, nz))
                    answer = answer.coerceAtLeast(box[nz][nr][nc])
                    tomatoes++
                }
            }

            println(if (tomatoes + spaces == n * m * h) answer else -1)
        }
    }
}

fun main() {
    소병희_토마토.solve()
}