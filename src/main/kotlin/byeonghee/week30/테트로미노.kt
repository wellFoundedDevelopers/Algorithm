package byeonghee.week30

import java.io.StreamTokenizer

class 소병희_테트로미노 {

    companion object {
        val dr = intArrayOf(0, 1, 0)
        val dc = intArrayOf(1, 0, -1)

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val m = input()
            val paper = Array(n) { IntArray(m) { input() } }
            val nowMino = Array(n) { BooleanArray(m) }
            var answer = 0

            fun makeTetromino(size: Int, sum: Int, mino: Array<IntArray>) {
                if (size == 4) {
                    answer = answer.coerceAtLeast(sum)
                    return
                }

                for((r, c) in mino) for(d in 0 until 3) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr !in 0 until n || nc !in 0 until m) continue
                    if (nowMino[nr][nc]) continue

                    nowMino[nr][nc] = true
                    makeTetromino(size+1, sum + paper[nr][nc], mino.plus(intArrayOf(nr, nc)))
                    nowMino[nr][nc] = false
                }
            }

            for(r in 0 until n) for(c in 0 until m) {
                nowMino[r][c] = true
                makeTetromino(1, paper[r][c], arrayOf(intArrayOf(r, c)))
                nowMino[r][c] = false
            }

            print(answer)
        }
    }
}

fun main() {
    소병희_테트로미노.solve()
}