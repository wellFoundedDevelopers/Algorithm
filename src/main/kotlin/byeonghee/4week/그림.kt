package byeonghee.`4week`

import java.io.*

class  `소병희_그림` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var n = 0
        var m = 0
        var canvas = Array(502){ IntArray(502) }

        data class Pos(val r : Int, val c: Int)
        val adjacents =  listOf(
            Pos(-1, 0),
            Pos(0, -1),
            Pos(0, 1),
            Pos(1, 0)
        )

        var q = ArrayDeque<Pos>()

        var drawCount = 0
        var drawSize = 0
        var maxDrawSize = 0


        fun solution() {
            br.readLine().split(" ").run {
                n = first().toInt()
                m = last().toInt()
            }
            canvas = Array(n) { br.readLine().split(" ").map{ it.toInt() }.toIntArray() }

            for(i in 0 until n) for(j in 0 until m) {
                if (canvas[i][j] == 0) continue
                canvas[i][j] = 0
                drawCount++
                drawSize = 1

                q.addLast(Pos(i, j))
                while(q.isNotEmpty()) {
                    q.removeFirst().run {
                        for((dr, dc) in adjacents) {
                            if (r + dr in 0 until n
                                && c + dc in 0 until m
                                && canvas[r + dr][c + dc] == 1
                            ) {
                                drawSize++
                                canvas[r + dr][c + dc] = 0
                                q.addLast(Pos(r + dr, c + dc))
                            }
                        }
                    }
                }
                maxDrawSize = Integer.max(maxDrawSize, drawSize)
            }

            println(drawCount)
            println(maxDrawSize)
        }
    }
}

fun main() {
    `소병희_그림`.getSolution().solution()
}