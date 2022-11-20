package byeonghee.`10week`

import java.io.*

class 소병희_달팽이 {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))

        data class Pos(var r: Int, var c: Int) {
            operator fun plusAssign(pos: Pos) {
                r += pos.r
                c += pos.c
            }

            override fun toString(): String {
                return "${r + 1} ${c + 1}"
            }
        }
        val mv = listOf(
            Pos(1, 0),
            Pos(0, 1),
            Pos(-1, 0),
            Pos(0, -1)
        )
        val diagonal = Pos(1, 1)

        lateinit var table : Array<IntArray>
        lateinit var targetPos : Pos
        var cur = Pos(0, 0)
        var num = 0

        fun solve() {
            val n = br.readLine().toInt()
            val target = br.readLine().toInt()

            table = Array(n) { IntArray(n) }
            targetPos = Pos(n/2, n/2)
            num = n * n
            for(size in n - 1 downTo 2 step 2) {
                for (d in mv) {
                    repeat(size) {
                        if (num == target) targetPos = cur.copy()
                        table[cur.r][cur.c] = num--
                        cur += d
                    }
                }
                cur += diagonal
            }
            table[n / 2][n / 2] = 1

            println(table.joinToString("\n") { it.joinToString(" ") })
            println(targetPos)
        }
    }
}

fun main() {
    소병희_달팽이.solve()
}