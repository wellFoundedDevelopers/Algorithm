package byeonghee.week64

class 소병희_내리막길 {
    companion object {
        val dr = intArrayOf(0, 1, 0, -1)
        val dc = intArrayOf(1, 0, -1, 0)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (m, n) = readLine().split(" ").map { it.toInt() }
            val map = Array(m) { IntArray(n) }
            val visited = Array(m) { IntArray(n) { -1 } }

            repeat(m) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    map[i][j] = v.toInt()
                }
            }

            visited[m-1][n-1] = 1

            fun dfs(r: Int, c: Int): Int {
                if (visited[r][c] > -1) return visited[r][c]

                visited[r][c] = 0
                for(d in 0 until 4) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr !in 0 until m || nc !in 0 until n) continue
                    if (map[nr][nc] >= map[r][c]) continue

                    visited[r][c] += dfs(nr, nc)
                }

                return visited[r][c]
            }

            println(dfs(0, 0))
        }
    }
}

fun main() {
    소병희_내리막길.solve()
}