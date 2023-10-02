package byeonghee.week46

class 소병희_농장관리 {

    companion object {
        val dr = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val dc = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val farm = Array(n) { IntArray(m) }
            val notTop = Array(n) { BooleanArray(m) }
            val isTop = Array(n) { BooleanArray(m) }
            var answer = 0

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    farm[i][j] = v.toInt()
                }
            }

            row@ for(r in 0 until n) {
                col@ for(c in 0 until m) {
                    if (notTop[r][c] || isTop[r][c]) continue

                    var same = 0
                    for(d in 0 until 8) {
                        val nr = r + dr[d]
                        val nc = c + dc[d]
                        if (nr !in 0 until n || nc !in 0 until m) continue
                        if (farm[nr][nc] > farm[r][c]) continue@col
                        if (farm[nr][nc] == farm[r][c] && isTop[nr][nc]) {
                            isTop[r][c] = true
                            continue@col
                        }
                        if (farm[nr][nc] == farm[r][c] && notTop[nr][nc]) {
                            notTop[r][c] = true
                            continue@col
                        }
                        if (farm[nr][nc] == farm[r][c]) same++
                        if (same > 1) continue@col
                    }

                    answer++
                    isTop[r][c] = true

                    for(d in 0 until 8) {
                        val nr = r + dr[d]
                        val nc = c + dc[d]
                        if (nr !in 0 until n || nc !in 0 until m) continue
                        if (farm[nr][nc] == farm[r][c]) isTop[nr][nc] = true
                        else notTop[nr][nc] = true
                    }
                }
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_농장관리.solve()
}