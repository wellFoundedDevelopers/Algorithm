package byeonghee.`21week`

import java.io.*

class 소병희_알파벳 {

    companion object {
        val dr = intArrayOf(0, 1, 0, -1)
        val dc = intArrayOf(1, 0, -1, 0)

        var answer = 0
        lateinit var board : Array<IntArray>
        lateinit var traces : Array<IntArray>
        lateinit var rRange : IntRange
        lateinit var cRange : IntRange

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (r, c) = readLine().split(" ").map { it.toInt() }
            board = Array(r) { readLine().map { it - 'A' }.toIntArray() }

            traces = Array(r) { IntArray(c) }
            rRange = 0 until r
            cRange = 0 until c

            dfs(0, 0, 1, 1 shl board[0][0])
            println(answer)
        }

        fun dfs(r: Int, c: Int, d: Int, bm: Int) {
            if (traces[r][c] == bm) return
            traces[r][c] = bm

            answer = maxOf(answer, d)

            for(i in 0 until 4) {
                val newR = r + dr[i]
                val newC = c + dc[i]
                if (newR !in rRange || newC !in cRange) continue

                val newBm = 1 shl board[newR][newC]
                if (bm and newBm != 0) continue

                dfs(newR, newC, d + 1, bm or newBm)
            }
        }
    }
}

fun main() {
    소병희_알파벳.solve()
}