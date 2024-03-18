package byeonghee.week54

class 소병희_토마토 {
    companion object {
        const val TOMATO = 1
        const val UNRIPE = 0
        const val EMPTY = -1

        val dr = intArrayOf(1, 0, -1, 0)
        val dc = intArrayOf(0, 1, 0, -1)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (m, n) = readLine().split(" ").map { it.toInt() }
            val box = Array(n) { IntArray(m) }
            val q = ArrayDeque<IntArray>(n * m)
            var days = 0
            var unripe = 0

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    box[i][j] = v.toInt()
                    when (box[i][j]) {
                        TOMATO -> q.add(intArrayOf(i, j))
                        UNRIPE -> unripe++
                    }
                }
            }

            while(q.isNotEmpty()) {
                val (r, c) = q.removeFirst()
                for(d in 0 until 4) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr !in 0 until n || nc !in 0 until m) continue
                    if (box[nr][nc] != 0) continue

                    box[nr][nc] = box[r][c] + 1
                    q.add(intArrayOf(nr, nc))
                    unripe--
                }

                days = box[r][c]
            }

            if (unripe > 0) println(-1)
            else println(days-1)
        }
    }
}

fun main() {
    소병희_토마토.solve()
}