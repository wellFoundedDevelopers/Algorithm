package byeonghee.`10week`

import java.io.*
import kotlin.math.max

class `소병희_병든 나이트` {

    companion object {
        data class Pos(val r: Int, val c: Int) {
            operator fun plus(pos: Pos) : Pos {
                return Pos(r + pos.r, c + pos.c)
            }

            fun inBoard() : Boolean {
                return r in 0 until n && c in 0 until m
            }
        }

        val br = BufferedReader(InputStreamReader(System.`in`))
        val visited = IntArray(4)
        val mv = listOf(
            Pos(2, 1),
            Pos(1, 2),
            Pos(-1, 2),
            Pos(-2, 1)
        )

        var n = 0
        var m = 0
        var count = 1


        fun solve() {
            br.readLine().split(" ").map { it.toInt() }.let {
                n = it.component1()
                m = it.component2()
            }

            if (n >= 3 && m >= 7) {
                println(m - 2)
            }
            else {
                moveFourth(0, Pos(n-1, 0))
                println(count)
            }
        }

        fun moveFourth(depth: Int, pos: Pos) {
            if (pos.inBoard().not()) return

            if (depth == 4) {
                if (visited.none { it == 0 }) {
                    count = 5
                }
                return
            }
            else count = max(count, depth + 1)

            for (i in 0..3) {
                visited[i]++
                moveFourth(depth + 1, pos + mv[i])
                visited[i]--
            }
        }
    }
}

fun main() {
    `소병희_병든 나이트`.solve()
}