package byeonghee.week29

import java.io.StreamTokenizer

class 소병희_빙산 {

    companion object {
        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {

            val dr = intArrayOf(0, 1, 0, -1)
            val dc = intArrayOf(1, 0, -1, 0)

            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val m = input()
            val ice = Array(n) { IntArray(m) }
            val q = ArrayDeque<IntArray>(n * m)
            var iceCnt = 0
            var year = 0
            var nr = 0
            var nc = 0
            var nm = 0

            for(i in 0 until n) for(j in 0 until m) {
                ice[i][j] = input().also { if (it > 0) iceCnt++ }
            }

            while(iceCnt > 0) {
                val visited = Array(n) { BooleanArray(m) }
                var curCnt = 0
                var meltCnt = 0

                loop@for(i in 0 until n) for(j in 0 until m) {
                    if (ice[i][j] > 0) {
                        q.add(intArrayOf(i, j))
                        visited[i][j] = true
                        break@loop
                    }
                }

                while(q.isNotEmpty()) {
                    val (r, c) = q.removeFirst()
                    nm = 0
                    for(d in 0 until 4) {
                        nr = r + dr[d]
                        nc = c + dc[d]

                        if (nr !in 0 until n  || nc !in 0 until m) continue
                        if (visited[nr][nc]) continue

                        if (ice[nr][nc] > 0) {
                            visited[nr][nc] = true
                            q.add(intArrayOf(nr, nc))
                        }
                        else nm++
                    }
                    ice[r][c] = (ice[r][c] - nm).coerceAtLeast(0)
                    if (ice[r][c] == 0) meltCnt++
                    curCnt++
                }

                if (curCnt < iceCnt) {
                    print(year)
                    kotlin.system.exitProcess(0)
                }
                year++
                iceCnt -= meltCnt
            }

            print(0)
        }
    }
}

fun main() {
    소병희_빙산.solve()
}