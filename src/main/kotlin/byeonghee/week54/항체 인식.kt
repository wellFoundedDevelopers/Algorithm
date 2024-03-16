package byeonghee.week54

class 소병희_항체인식 {
    companion object {
        val dr = intArrayOf(-1, 0, 1, 0)
        val dc = intArrayOf(0, 1, 0, -1)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val before = Array(n) { IntArray(m) }
            val after = Array(n) { IntArray(m) }
            val visited = Array(n) { BooleanArray(m) }
            val q = ArrayDeque<IntArray>(n * m)
            var shot = false
            var answer = "YES"
            var origin = 0
            var change = -1

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    before[i][j] = v.toInt()
                }
            }

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    after[i][j] = v.toInt()
                }
            }

            outer@ for(i in 0 until n) inner@ for(j in 0 until m) {
                if (visited[i][j]) continue@inner
                if (shot && before[i][j] != after[i][j]) {
                    answer = "NO"
                    break@outer
                }

                origin = before[i][j].also { visited[i][j] = true }
                change = after[i][j].also { visited[i][j] = true }
                q.add(intArrayOf(i, j))

                while(q.isNotEmpty()) {
                    val (r, c) = q.removeFirst()
                    for(d in 0 until 4) {
                        val nr = r + dr[d]
                        val nc = c + dc[d]
                        if (nr !in 0 until n || nc !in 0 until m) continue
                        if (visited[nr][nc]) continue
                        if (before[nr][nc] == origin && after[nr][nc] == change) {
                            visited[nr][nc] = true
                            q.add(intArrayOf(nr, nc))
                        }
                        else if (before[nr][nc] != origin) {
                            continue
                        }
                        else {
                            answer = "NO"
                            break@outer
                        }
                    }
                }

                if (origin != change) {
                    shot = true
                }
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_항체인식.solve()
}