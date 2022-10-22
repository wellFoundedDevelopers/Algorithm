package byeonghee.`6week`

import java.io.*

class `소병희_아기 상어 2` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        data class Pos(val r : Int, val c : Int) {
            operator fun plus(p : Pos) : Pos {
                return Pos(r + p.r, c + p.c)
            }

            fun isInBound(h: Int, w: Int) : Boolean {
                return r in 0 until h && c in 0 until w
            }
        }

        val mvs = listOf(
            Pos(-1, 0),
            Pos(-1, 1),
            Pos(0, 1),
            Pos(1, 1),
            Pos(1, 0),
            Pos(1, -1),
            Pos(0, -1),
            Pos(-1, -1)
        )

        data class Safe(val p: Pos, val d: Int)

        var n = 0
        var m = 0
        var sea = Array(0) { IntArray(0) }

        var sharks = ArrayDeque<Safe>()
        var cur = Safe(Pos(0, 0), 0)
        var nxt = Pos(0, 0)


        fun solution() {
            br.readLine().split(" ").map{ it.toInt() }.run {
                n = first()
                m = last()
            }
            sea = Array(n) { IntArray(m){ Int.MAX_VALUE } }

            repeat(n) { r ->
                br.readLine().split(" ").let {
                    for(c in it.indices) {
                        if (it[c] == "1") sharks.add(Safe(Pos(r, c), 0))
                    }
                }
            }

            while(sharks.isNotEmpty()) {
                cur = sharks.removeFirst()
                if (sea[cur.p.r][cur.p.c] <= cur.d) continue

                with(cur) {
                    sea[p.r][p.c] = d
                    for (mv in mvs) {
                        nxt = p + mv
                        if (nxt.isInBound(n, m).not()) continue
                        if (sea[nxt.r][nxt.c] <= d + 1) continue
                        sharks.add(Safe(nxt, d + 1))
                    }
                }
            }

            println(sea.maxOf { it.maxOf{ it } })
        }
    }
}

fun main() {
    `소병희_아기 상어 2`.getSolution().solution()
}