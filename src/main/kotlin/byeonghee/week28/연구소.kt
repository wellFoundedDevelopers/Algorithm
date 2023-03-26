package byeonghee.week28

import java.io.StreamTokenizer

class 소병희_연구소 {

    companion object {
        const val BLANK = 0
        const val WALL = 1
        const val VIRUS = 2

        val dr = arrayOf(0, 1, 0, -1)
        val dc = arrayOf(1, 0, -1, 0)

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {

            fun input() : Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val m = input()
            val lab = Array(n) { IntArray(m) }
            var preSafe = n * m - 3
            var answer = 0
            var nr : Int
            var nc : Int

            for(i in 0 until n) for(j in 0 until m) {
                lab[i][j] = input()
                if (lab[i][j] == WALL) preSafe--
            }

            fun spreadVirus() {
                val visited = Array(n) { BooleanArray(m) }
                val q = ArrayDeque<Pair<Int, Int>>()
                var safeArea = preSafe

                for(r in 0 until n) for(c in 0 until m) {
                    if (lab[r][c] == VIRUS) q.add(Pair(r, c))
                }

                while(q.isNotEmpty()) {
                    val (r, c) = q.removeFirst()
                    if (visited[r][c]) continue

                    visited[r][c] = true
                    if (--safeArea == 0) break
                    for(d in 0 until 4) {
                        nr = r + dr[d]
                        nc = c + dc[d]
                        if (nr !in 0 until n || nc !in 0 until m) continue
                        if (visited[nr][nc] || lab[nr][nc] == WALL) continue
                        q.add(Pair(nr, nc))
                    }
                }

                answer = answer.coerceAtLeast(safeArea)
            }

            fun putUpWall(i: Int, rest: Int) {
                if (rest == 0) return spreadVirus()

                if (lab[i / m][i % m] == BLANK) {
                    lab[i / m][i % m] = WALL
                    putUpWall(i + 1, rest - 1)
                    lab[i / m][i % m] = BLANK
                }

                if (i + rest < n * m) putUpWall(i + 1, rest)
            }

            putUpWall(0, 3)
            print(answer)
        }
    }
}

fun main() {
    소병희_연구소.solve()
}