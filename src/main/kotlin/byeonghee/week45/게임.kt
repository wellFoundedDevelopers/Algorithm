package byeonghee.week45

class 소병희_게임 {

    companion object {
        const val INF = 999_999
        const val GOAL = 500
        const val DANGER = 1
        const val DEATH = 2

        val dx = intArrayOf(0, 1, 0, -1)
        val dy = intArrayOf(1, 0, -1, 0)

        fun solve() = with(System.`in`.bufferedReader()) {
            val board = Array(GOAL + 1) { IntArray(GOAL + 1) }
            val visited = Array(GOAL + 1) { IntArray(GOAL + 1) { INF } }
            val q = ArrayDeque<IntArray>()

            for(k in DANGER .. DEATH) {
                repeat(readLine().toInt()) {
                    val (x1, y1, x2, y2) = readLine().split(" ").map { it.toInt() }
                    val minX = minOf(x1, x2)
                    val maxX = maxOf(x1, x2)
                    val minY = minOf(y1, y2)
                    val maxY = maxOf(y1, y2)

                    for(x in minX .. maxX) for(y in minY .. maxY) {
                        board[x][y] = k
                    }
                }
            }

            if (board[GOAL][GOAL] == DEATH) {
                println(-1)
                return@with
            }

            q.add(intArrayOf(0, 0))
            visited[0][0] = 0

            while(q.isNotEmpty()) {
                val (x, y) = q.removeFirst()

                for(d in 0 until 4) {
                    val nx = x + dx[d]
                    val ny = y + dy[d]
                    if (nx !in 0..500 || ny !in 0..500) continue

                    if (board[nx][ny] == DEATH) continue
                    val nl = visited[x][y] + if (board[nx][ny] == DANGER) 1 else 0
                    if (visited[nx][ny] <= nl) continue
                    visited[nx][ny] = nl

                    q.add(intArrayOf(nx, ny))
                }
            }

            if (visited[GOAL][GOAL] == INF) println(-1)
            else println(visited[GOAL][GOAL])
        }
    }
}

fun main() {
    소병희_게임.solve()
}