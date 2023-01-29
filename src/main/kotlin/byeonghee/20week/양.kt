package byeonghee.`20week`

import java.io.*

class 소병희_양 {

    companion object {
        const val FENCE = '#'
        const val SHEEP = 'o'
        const val WOLF = 'v'

        val mv = listOf(
            Pair(-1, 0),
            Pair(0, 1),
            Pair(1, 0),
            Pair(0, -1)
        )

        lateinit var backyard : Array<CharArray>
        var localSheep = 0
        var localWolf = 0

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (R, C) = readLine().split(" ").map { it.toInt() }
            backyard = Array(R + 2) { CharArray(C + 2) { '#' } }
            val visitQ = ArrayDeque<Pair<Int, Int>>()
            var sheep = 0
            var wolf = 0

            for(i in 0 until R) {
                readLine().forEachIndexed { j, v ->
                    backyard[i+1][j+1] = v
                    when (v) {
                        SHEEP, WOLF -> visitQ.addLast(Pair(i+1, j+1))
                    }
                }
            }

            while(visitQ.isNotEmpty()) {
                val (r, c) = visitQ.removeFirst()
                if (backyard[r][c] == FENCE) continue

                dfsForSheep(r, c)
                if (localSheep > localWolf) sheep += localSheep
                else wolf += localWolf

                localSheep = 0
                localWolf = 0
            }

            println("$sheep $wolf")
        }

        fun dfsForSheep(r: Int, c: Int) {
            when(backyard[r][c]) {
                FENCE -> return
                SHEEP -> localSheep++
                WOLF -> localWolf++
            }

            backyard[r][c] = FENCE

            for((dr, dc) in mv) {
                dfsForSheep(r + dr, c + dc)
            }
        }
    }
}

fun main() {
    소병희_양.solve()
}