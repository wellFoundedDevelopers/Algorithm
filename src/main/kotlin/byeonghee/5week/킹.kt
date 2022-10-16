package byeonghee.`5week`

import java.io.*

class `소병희_킹` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }


    class Solution {
        companion object {
            fun Char.toIdx() : Int {
                return if (this.isDigit()) (digitToInt() - 1) else code - 'A'.code
            }

            fun Int.isInside() : Boolean {
                return this in 0 until 8
            }


            fun Int.toBoard() : Char {
                return (this + 'A'.code).toChar()
            }
        }

        val br = BufferedReader(InputStreamReader(System.`in`))

        data class Pos(val r: Int, val c: Int) {
            operator fun plus(pos: Pos): Pos {
                val newR = r + pos.r
                val newC = c + pos.c
                return Pos(newR, newC)
            }

            fun isInside() = r.isInside() && c.isInside()

            override fun toString() : String {
                return c.toBoard() + (r + 1).toString()
            }
        }

        fun solution() {
            val kingMoving = mapOf(
                Pair("R", Pos(0, 1)),
                Pair("L", Pos(0, -1)),
                Pair("B", Pos(-1, 0)),
                Pair("T", Pos(1, 0)),
                Pair("RT", Pos(1, 1)),
                Pair("LT", Pos(1, -1)),
                Pair("RB", Pos(-1, 1)),
                Pair("LB", Pos(-1, -1))
            )

            val (king, stone, n) = br.readLine().split(" ")
            var kingPos = Pos(king[1].toIdx(), king[0].toIdx())
            var stonePos = Pos(stone[1].toIdx(), stone[0].toIdx())

            val moves = mutableListOf<Pos>()
            repeat(n.toInt()) {
                moves.add( kingMoving.getOrDefault(br.readLine(), Pos(0, 0)))
            }

            for(move in moves) {
                if (kingPos + move == stonePos) {
                    if ((stonePos + move).isInside()) {
                        kingPos = stonePos
                        stonePos += move
                    }
                }
                else if ((kingPos + move).isInside()) {
                    kingPos += move
                }
            }

            println("$kingPos")
            println("$stonePos")
        }
    }
}

fun main() {
    `소병희_킹`.getSolution().solution()
}