package byeonghee.week50

class 소병희_연구소2 {
    companion object {
        const val WALL_STR = "1"
        const val VIRUS_STR = "2"
        const val INI = 150
        const val WALL = -1

        val dr = intArrayOf(-1, 0, 1, 0)
        val dc = intArrayOf(0, 1, 0, -1)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val lab = Array(n) { IntArray(n) { INI } }
            val vList = Array(10) { IntArray(2) }
            val q = ArrayDeque<IntArray>()
            var vCnt = 0
            var eCnt = 0
            var answer = INI

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    when(v) {
                        VIRUS_STR -> vList[vCnt++] = intArrayOf(i, j)
                        WALL_STR -> lab[i][j] = WALL
                        else -> eCnt++
                    }
                }
            }
            eCnt += vCnt - m

            fun spreadVirus(q: ArrayDeque<IntArray>) {
                val visited = Array(n) { i -> IntArray(n) { j -> lab[i][j] } }
                var curTime = 0
                var restSpace = eCnt

                for((r, c, t) in q) {
                    visited[r][c] = t
                }

                q@ while(q.isNotEmpty()) {
                    val (r, c, t) = q.removeFirst()
                    for (d in 0 until 4) {
                        val nr = r + dr[d]
                        val nc = c + dc[d]
                        if (nr !in 0 until n || nc !in 0 until n) continue
                        if (visited[nr][nc] <= t) continue
                        visited[nr][nc] = t + 1
                        curTime = curTime.coerceAtLeast(t + 1)
                        restSpace--
                        if (restSpace == 0) break@q
                        q.addLast(intArrayOf(nr, nc, t + 1))
                    }
                }
                if (restSpace == 0) answer = answer.coerceAtMost(curTime)
            }

            fun putVirus(rest: Int, idx: Int) {
                if (rest == 0) {
                    val nq = ArrayDeque(q)
                    spreadVirus(nq)
                    return
                }

                for(i in idx .. vCnt - rest) {
                    q.addLast(intArrayOf(vList[i][0], vList[i][1], 0))
                    putVirus(rest - 1, i + 1)
                    q.removeLast()
                }
                if (vCnt - idx >= rest) putVirus(rest, idx + 1)
            }

            putVirus(m, 0)
            println(if (answer == INI) -1 else answer)
        }
    }
}

fun main() {
    소병희_연구소2.solve()
}