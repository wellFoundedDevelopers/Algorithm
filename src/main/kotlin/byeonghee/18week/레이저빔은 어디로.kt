package byeonghee.`18week`

import java.io.*

class 소병희_레이저빔은어디로 {

    companion object {
        const val UP = 0
        const val RIGHT = 1
        const val DOWN = 2
        const val LEFT = 3
        const val MIRROR = 4

        data class Pos(val r: Int, val c: Int) {
            fun move() : Pos {
                return Pos(r + mv[curD].r, c + mv[curD].c)
            }

            fun onBoard(n: Int) : Boolean {
                return r in 1..n && c in 1..n
            }

            fun onMirror() : Boolean {
                return board[r][c] and 1.shl(MIRROR) != 0
            }

            fun isCycle() : Boolean {
                return board[r][c] and 1.shl(curD) != 0
            }

            override fun toString() : String {
                return String.format("%d %d", r, c)
            }
        }

        val mv = listOf(
            Pos(-1, 0),
            Pos(0, 1),
            Pos(1, 0),
            Pos(0, -1)
        )

        lateinit var board : Array<IntArray>
        lateinit var curP : Pos
        var curD = -1

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            repeat(readLine().toInt()) {
                val (N, R) = readLine().split(" ").map { it.toInt() }
                board = Array(N + 2) { IntArray(N + 2) }

                repeat(R) {
                    val (r, c) = readLine().split(" ").map { it.toInt() }
                    board[r][c] = 1.shl(MIRROR)
                }

                val (laserR, laserC) = readLine().split(" ").map { it.toInt() }
                curD =
                    if (laserR == N + 1) UP
                    else if (laserC == 0) RIGHT
                    else if (laserR == 0) DOWN
                    else LEFT

                curP = Pos(laserR, laserC).move()
                board[laserR][laserC] += 1.shl(curD)

                while(curP.onBoard(N)) {
                    if (curP.isCycle()) {
                        curP = Pos(0, 0)
                        break
                    }
                    board[curP.r][curP.c] += 1.shl(curD)

                    if (curP.onMirror()) {
                        curD = (curD + 1) % 4
                    }
                    curP = curP.move()
                }
                println(curP)
            }
        }
    }
}

fun main() {
    소병희_레이저빔은어디로.solve()
}