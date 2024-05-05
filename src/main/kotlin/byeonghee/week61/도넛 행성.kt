package byeonghee.week61

class 소병희_도넛행성 {
    companion object {
        const val FOREST = 1
        const val VISITED = -1

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val q = ArrayDeque<Pair<Int, Int>>()
            val donut = Array(n) { IntArray(m) }
            val dr = intArrayOf(1, 0, n-1, 0)
            val dc = intArrayOf(0, 1, 0, m-1)
            var answer = 0

            for(i in 0 until n) {
                readLine().split(" ").forEachIndexed { j, v ->
                    donut[i][j] = v.toInt()
                }
            }

            for(i in 0 until n) for(j in 0 until m) {
                if (donut[i][j] == VISITED || donut[i][j] == FOREST) continue
                donut[i][j] = VISITED
                answer++
                q.add(i to j)

                while(q.isNotEmpty()) {
                    val (r, c) = q.removeFirst()
                    for(d in 0 until 4) {
                        val nr = (r + dr[d]) % n
                        val nc = (c + dc[d]) % m
                        if (donut[nr][nc] == VISITED || donut[nr][nc] == FOREST) continue
                        donut[nr][nc] = VISITED
                        q.add(nr to nc)
                    }
                }
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_도넛행성.solve()
}