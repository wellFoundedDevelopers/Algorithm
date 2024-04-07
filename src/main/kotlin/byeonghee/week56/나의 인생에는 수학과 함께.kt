package byeonghee.week56

class 소병희_나의인생에는수학과함께 {
    companion object {
        val dr = intArrayOf(1, 0)
        val dc = intArrayOf(0, 1)
        val dOpc = arrayOf("+", "-", "*")
        val dOp = arrayOf(
                { a: Int, b: Int -> a + b },
                { a: Int, b: Int -> a - b },
                { a: Int, b: Int -> a * b }
        )

        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val board = Array(n) { readLine().split(" ") }
            val q = ArrayDeque<IntArray>(300)
            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE

            q.add(intArrayOf(0, 0, board[0][0].toInt(), -1, 0))
            while(q.isNotEmpty()) {
                for(d in 0..1) {
                    var (r, c, v1, op, v2) = q.first()
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr !in 0 until n || nc !in 0 until n) continue

                    if (board[nr][nc] !in dOpc) {
                        if (op == -1)
                            v1 = v1 * 10 + board[nr][nc].toInt()
                        else v2 = v2 * 10 + board[nr][nc].toInt()
                        if (nr == n-1 && nc == n-1) {
                            min = min.coerceAtMost(dOp[op](v1, v2))
                            max = max.coerceAtLeast(dOp[op](v1, v2))
                        }
                    }
                    else {
                        if (op != -1) {
                            v1 = dOp[op](v1, v2)
                        }
                        op = dOpc.indexOf(board[nr][nc])
                        v2 = 0
                    }

                    q.add(intArrayOf(nr, nc, v1, op, v2))
                }
                q.removeFirst()
            }

            println("$max $min")
        }
    }
}

fun main() {
    소병희_나의인생에는수학과함께.solve()
}