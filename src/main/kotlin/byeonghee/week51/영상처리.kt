package byeonghee.week51

class 소병희_영상처리 {
    companion object {
        val dr = intArrayOf(-1, 0, 1, 0)
        val dc = intArrayOf(0, 1, 0, -1)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val screen = Array(n) { IntArray(m) }
            val visited = Array(n) { BooleanArray(m) }
            var stuff = 0

            repeat(n) { i ->
                val line = readLine().split(" ").map { it.toInt() }
                repeat(m) { j ->
                    screen[i][j] = (line[j*3] + line[j*3 + 1] + line[j*3 + 2]) / 3
                }
            }

            val t = readLine().toInt()

            for(i in 0 until n) for(j in 0 until m) {
                screen[i][j] = if (screen[i][j] >= t) 255 else 0
            }

            fun bfs(i: Int, j: Int) {
                val q = ArrayDeque<IntArray>()
                q.add(intArrayOf(i, j))
                screen[i][j] = stuff

                while(q.isNotEmpty()) {
                    val (r, c) = q.removeFirst()

                    for(d in 0 until 4) {
                        val nr = r + dr[d]
                        val nc = c + dc[d]
                        if (nr !in 0 until n || nc !in 0 until m) continue

                        if (visited[nr][nc]) continue
                        visited[nr][nc] = true

                        if(screen[nr][nc] == 255) {
                            screen[nr][nc] = stuff
                            q.add(intArrayOf(nr, nc))
                        }
                    }
                }
            }

            for(i in 0 until n) for(j in 0 until m) {
                if (visited[i][j]) continue
                visited[i][j] = true

                if (screen[i][j] == 255) {
                    stuff++
                    bfs(i, j)
                }
            }

            println(stuff)
        }
    }
}

fun main() {
    소병희_영상처리.solve()
}