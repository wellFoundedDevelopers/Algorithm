package byeonghee.week39

import java.io.*

class 소병희_봄버맨 {

    companion object {
        val dr = intArrayOf(-1, 0, 1, 0)
        val dc = intArrayOf(0, 1, 0, -1)

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (r, c, n) = readLine().split(" ").map { it.toInt() }
            val board = Array(r) { IntArray(c) }
            val sb = StringBuilder()
            var predicate: (Int, Int, Int) -> Unit

            if (n%2 == 0) {
                repeat(r) { sb.appendLine("O".repeat(c)) }
                print(sb)
                return@with
            }

            for(i in 0 until r) {
                readLine().forEachIndexed { j, v -> board[i][j] = if (v == '.') -1 else 3 }
            }

            fun setBomb(i: Int, j: Int, t: Int) {
                if (board[i][j] == -1) board[i][j] = t + 3
            }

            fun bombBomb(i: Int, j: Int, t: Int) {
                if (board[i][j] == t) {
                    board[i][j] = -1
                    for(d in 0 until 4) {
                        val rr = i + dr[d]
                        val cc = j + dc[d]
                        if (rr !in 0 until r || cc !in 0 until c) continue
                        if (board[rr][cc] > t) {
                            board[rr][cc] = -1
                        }
                    }
                }
            }

            for(t in 2 .. n) {
                predicate = if (t%2 == 0) ::setBomb else ::bombBomb
                for(i in 0 until r) for(j in 0 until c) {
                    predicate(i, j, t)
                }
            }

            for(i in 0 until r) {
                for(j in 0 until c) {
                    sb.append(if (board[i][j] == -1) '.' else 'O')
                }
                sb.appendLine()
            }

            print(sb)
        }
    }
}

fun main() {
    소병희_봄버맨.solve()
}