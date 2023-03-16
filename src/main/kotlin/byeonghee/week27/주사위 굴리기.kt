package byeonghee.week27

import java.io.*

class 소병희_주사위굴리기 {

    companion object {
        val sb = StringBuilder()
        val dr = arrayOf(0, 0, -1, 1)
        val dc = arrayOf(1, -1, 0, 0)
        val rotations = arrayOf(
            arrayOf(6, 0, 3, 5, 2, 6),
            arrayOf(6, 2, 5, 3, 0, 6),
            arrayOf(6, 0, 1, 5, 4, 6),
            arrayOf(6, 4, 5, 1, 0, 6)
        )

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {

            var (n, m, r, c, k) = readLine().split(" ").map { it.toInt() }
            val map = Array(n) { IntArray(m) }
            val order = IntArray(k)
            val dice = IntArray(7)

            repeat(n) { r ->
                readLine().split(" ").forEachIndexed { c, v ->
                    map[r][c] = v.toInt()
                }
            }

            readLine().split(" ").forEachIndexed { i, v ->
                order[i] = v.toInt() - 1
            }

            for(o in order) {
                if (r + dr[o] !in 0 until n || c + dc[o] !in 0 until m) continue

                r += dr[o]
                c += dc[o]
                for(i in 0 .. 4) {
                    dice[rotations[o][i]] = dice[rotations[o][i + 1]]
                }

                when(map[r][c]) {
                    0 -> map[r][c] = dice[5]
                    else -> dice[5] = map[r][c].also { map[r][c] = 0 }
                }

                sb.appendLine(dice[0])
            }
            print(sb)
        }
    }
}

fun main() {
    소병희_주사위굴리기.solve()
}