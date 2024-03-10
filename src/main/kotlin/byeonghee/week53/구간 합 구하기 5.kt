package byeonghee.week53

class 소병희_구간합구하기5 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val table = Array(n + 1) { IntArray(n + 1) }
            val sb = StringBuilder()

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    table[i+1][j+1] = table[i+1][j] + v.toInt()
                }
            }

            repeat(n) { i ->
                repeat(n) { j ->
                    table[i+1][j+1] += table[i][j+1]
                }
            }

            repeat(m) {
                val (r1, c1, r2, c2) = readLine().split(" ").map { it.toInt() }
                sb.appendLine(table[r2][c2] + table[r1-1][c1-1] - table[r1-1][c2] - table[r2][c1-1])
            }

            println(sb)
        }
    }
}

fun main() {
    소병희_구간합구하기5.solve()
}