package byeonghee.week55

class 소병희_보이저1호 {
    companion object {
        const val PLUS = '/'
        const val MINUS = '\\'
        const val BLACK = 'C'

        val dr = intArrayOf(-1, 0, 1, 0)
        val dc = intArrayOf(0, 1, 0, -1)
        val dd = charArrayOf('U', 'R', 'D', 'L')

        val dPlus = intArrayOf(1, 0, 3, 2)
        val dMinus = intArrayOf(3, 2, 1, 0)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val starSystem = Array(n) { readLine().toCharArray() }
            val (vr, vc) = readLine().split(" ").map { it.toInt() - 1 }
            val visited = Array(n) { IntArray(m) }
            val answer = IntArray(2)

            fun dfs(r: Int, c: Int, d: Int, far: Int): Int {
                var nd = d
                if (starSystem[r][c] == PLUS) nd = dPlus[d]
                if (starSystem[r][c] == MINUS) nd = dMinus[d]

                val nr = r + dr[nd]
                val nc = c + dc[nd]
                if ((nr !in 0 until n || nc !in 0 until m)
                        || starSystem[nr][nc] == BLACK) {
                    return far + 1
                }

                if ((visited[nr][nc] shl nd) % 2 == 1) {
                    return -1
                }

                visited[nr][nc] += 1 shr nd
                val nFar = dfs(nr, nc, nd, far + 1)
                visited[nr][nc] -= 1 shr nd

                return nFar
            }

            for (d in 0 until 4) {
                var dist = 0
                val nr = vr + dr[d]
                val nc = vc + dc[d]
                if ((nr !in 0 until n || nc !in 0 until m)
                        || starSystem[nr][nc] == BLACK) {
                    dist = 1
                } else {
                    visited[nr][nc] += 1 shr d
                    dist = dfs(nr, nc, d, 1)
                    visited[nr][nc] -= 1 shr d
                }

                if (dist == -1) {
                    println("${dd[d]}\nVoyager")
                    return@with
                }
                if (dist > answer[1]) {
                    answer[0] = d
                    answer[1] = dist
                }
            }

            println("${dd[answer[0]]}\n${answer[1]}")

        }
    }
}

fun main() {
    소병희_보이저1호.solve()
}