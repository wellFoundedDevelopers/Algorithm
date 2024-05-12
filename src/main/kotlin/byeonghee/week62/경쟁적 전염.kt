package byeonghee.week62

class `소병희_경쟁적 전염` {
    companion object {
        val dr = intArrayOf(-1, 0, 1, 0)
        val dc = intArrayOf(0, 1, 0, -1)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, k) = readLine().split(" ").map { it.toInt() }
            val tube = Array(n) { IntArray(n) }
            val time = Array(n) { IntArray(n) }
            val q = ArrayDeque<IntArray>(n * n)

            for(i in 0 until n) {
                readLine().split(" ").forEachIndexed { j, v ->
                    tube[i][j] = v.toInt()
                    if (tube[i][j] > 0) q.add(intArrayOf(i, j, tube[i][j], 0))
                }
            }

            val (s, x, y) = readLine().split(" ").map { it.toInt() }

            while(q.isNotEmpty()) {
                val (r, c) = q.removeFirst()
                if (time[r][c] == s) continue

                for(d in 0 until 4) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr !in 0 until n || nc !in 0 until n) continue
                    if (tube[nr][nc] in 1 until tube[r][c]) continue
                    if (tube[nr][nc] > 0 && time[nr][nc] <= time[r][c]) continue

                    tube[nr][nc] = tube[r][c]
                    time[nr][nc] = time[r][c] + 1
                    q.add(intArrayOf(nr, nc))
                }
            }

            println(tube[x-1][y-1])
        }
    }
}

fun main() {
    `소병희_경쟁적 전염`.solve()
}