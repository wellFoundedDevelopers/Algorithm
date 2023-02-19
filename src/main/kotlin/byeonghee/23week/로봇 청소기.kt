package byeonghee.`23week`

import java.io.*

class 소병희_로봇청소기 {

    companion object {
        const val DIRTY = 0
        const val WALL = 1
        const val CLEAR = 2

        val dr = arrayOf(-1, 0, 1, 0)
        val dc = arrayOf(0, 1, 0, -1)

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            var (r, c, d) = readLine().split(" ").map { it.toInt() }
            val room = Array(n) { IntArray(m) }

            for(r in 0 until n) {
                readLine().split(" ").forEachIndexed { c, v ->
                    room[r][c] = v.toInt()
                }
            }

            var stop = false
            var back = false
            var cleaned = 0
            while(stop.not()) {
                if (room[r][c] == DIRTY) {
                    cleaned++
                    room[r][c] = CLEAR
                }

                back = true
                for (i in 0 until 4) {
                    d = (d + 3) % 4
                    if (room[r + dr[d]][c + dc[d]] == DIRTY) {
                        r += dr[d]
                        c += dc[d]
                        back = false
                        break
                    }
                }
                if (back.not()) continue

                if (room[r - dr[d]][c - dc[d]] == WALL) {
                    stop = true
                }
                else {
                    r -= dr[d]
                    c -= dc[d]
                }
            }

            println(cleaned)
        }
    }
}

fun main() {
    소병희_로봇청소기.solve()
}