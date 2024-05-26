package byeonghee.week64

class 소병희_치즈 {
    companion object {
        val dr = intArrayOf(0, 1, 0, -1)
        val dc = intArrayOf(1, 0, -1, 0)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val paper = Array(n) { IntArray(m) }
            var airContact = Array(n) { IntArray(m) }
            val q = ArrayDeque<IntArray>()
            var time = 0
            var cheese = 0

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    paper[i][j] = v.toInt().also { if (it == 1) cheese++ }
                }
            }

            fun checkAir() {
                airContact = Array(n) { IntArray(m) }
                airContact[0][0]++
                q.add(intArrayOf(0, 0))

                while(q.isNotEmpty()) {
                    val (r, c) = q.removeFirst()
                    for(d in 0 until 4) {
                        val nr = r + dr[d]
                        val nc = c + dc[d]
                        if (nr !in 0 until n || nc !in 0 until m) continue
                        if (paper[nr][nc] == 0 && airContact[nr][nc] > 0) continue

                        airContact[nr][nc]++
                        if (paper[nr][nc] == 1) continue
                        q.add(intArrayOf(nr, nc))
                    }
                }
            }

            while(cheese > 0) {
                checkAir()
                for(i in 0 until n) for(j in 0 until m) {
                    if (paper[i][j] == 1 && airContact[i][j] >= 2) {
                        paper[i][j] = 0
                        cheese--
                    }
                }
                time++
            }

            println(time)
        }
    }
}

fun main() {
    소병희_치즈.solve()
}