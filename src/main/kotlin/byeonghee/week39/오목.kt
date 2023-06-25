package byeonghee.week39

import java.io.StreamTokenizer
import kotlin.system.exitProcess

class 소병희_오목 {

    companion object {
        val dr = intArrayOf(0, 1, 1, 1)
        val dc = intArrayOf(1, 1, 0, -1)
        val ir = intArrayOf(1, 1, 0, 0)
        val ic = intArrayOf(0, 0, 1, 1)
        val w = intArrayOf(5, 5, 5, 1)

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input() : Int {
                nextToken()
                return nval.toInt()
            }

            val board = Array(21) { IntArray(21) }
            var color = 0
            var success = 0
            var rr = 0
            var cc = 0

            for(r in 1 .. 19) {
                for(c in 1 .. 19) {
                    board[r][c] = input()
                }
            }

            for(d in 0 until 4) {
                for(i in 0 until 21) {
                    rr = i * ir[d]
                    cc = i * ic[d]
                    color = board[rr][cc]
                    success = 1

                    for(j in 1 until 21) {
                        rr += dr[d]
                        cc += dc[d]
                        if (rr !in 0 until 21 || cc !in 0 until 21) continue

                        if (board[rr][cc] != color) {
                            if (success == 5) {
                                println(color)
                                println("${rr - w[d] * dr[d]} ${cc - w[d] * dc[d]}")
                                exitProcess(0)
                            }
                            color = board[rr][cc]
                            success = 1
                        }
                        else if (board[rr][cc] > 0) success++
                    }
                }
            }

            println(0)
        }
    }
}

fun main() {
    소병희_오목.solve()
}