package byeonghee.`5week`

import java.io.*
import kotlin.system.exitProcess

class `소병희_빙고` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        companion object {
            const val BINGO = 5
        }

        data class Pos(val r: Int = -1, val c: Int = -1)

        val br = BufferedReader(InputStreamReader(System.`in`))
        private val bingo = Array(BINGO) { BooleanArray(BINGO){ false } }
        private val chulsu = Array(26) { Pos() }

        private var answer = 0
        private var rightCross = 0
        private var leftCross = 0

        fun solution() {
            repeat(BINGO) { r ->
                br.readLine().split(" ").map { it.toInt() }.forEachIndexed { c, v ->
                    chulsu[v] = Pos(r, c)
                }
            }

            repeat(BINGO) { resultR ->
                br.readLine().split(" ").map { it.toInt() }.forEachIndexed { resultC, v ->
                    chulsu[v].run {
                        bingo[r][c] = true
                        if (bingo[r].all { it }) answer++
                        if (bingo.all { it[c] }) answer++
                        if (r == c) (++rightCross).also { if (it == BINGO) { answer++ } }
                        if ((r + c) == (BINGO - 1)) (++leftCross).also { if (it == BINGO) { answer++ } }
                    }

                    if (answer >= 3) {
                        println(resultR * BINGO + resultC + 1)
                        exitProcess(0)
                    }
                }
            }
        }
    }
}

fun main() {
    `소병희_빙고`.getSolution().solution()
}