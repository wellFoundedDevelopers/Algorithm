package byeonghee.`25week`

import java.io.*

class 소병희_톱니바퀴 {

    companion object {
        const val R = 2
        const val L = 6

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {

            val cogwheels = Array(4) { IntArray(8) }
            for(r in 0 until 4) {
                readLine().forEachIndexed { c, v ->
                    cogwheels[r][c] = v - '0'
                }
            }

            fun rotate(n: Int, d: Int, from: Int) {
                if (from != L && n > 0 && cogwheels[n - 1][R] != cogwheels[n][L]) {
                    rotate(n - 1, d * -1, R)
                }
                if (from != R && n < 3 && cogwheels[n + 1][L] != cogwheels[n][R]) {
                    rotate(n + 1, d * -1, L)
                }

                val save = cogwheels[n][0]
                for(i in 0 until 8) {
                    cogwheels[n][(8 - d * i) % 8] = cogwheels[n][(8 - d * (i + 1)) % 8]
                }
                cogwheels[n][(8 + d) % 8] = save
            }

            var n = 0
            var d = 0
            repeat(readLine().toInt()) {
                readLine().split(" ").run {
                    n = first().toInt() - 1
                    d = last().toInt()
                }

                rotate(n, d, 0)
            }

            var answer = 0
            for(i in 0 until 4) {
                answer += cogwheels[i][0] shl i
            }
            println(answer)
        }
    }
}

fun main() {
    소병희_톱니바퀴.solve()
}