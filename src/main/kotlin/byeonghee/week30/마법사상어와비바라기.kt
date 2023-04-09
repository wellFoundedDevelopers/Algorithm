package byeonghee.week30

import java.io.StreamTokenizer

class 소병희_마법사상어와비바라기 {

    companion object {
        val dr = intArrayOf(0, 0, -1, -1, -1, 0, 1, 1, 1)
        val dc = intArrayOf(0, -1, -1, 0, 1, 1, 1, 0, -1)

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val m = input()
            val offset = 50 / n + 1
            val basket = Array(n) { IntArray(n) { input() } }
            val clouds = ArrayDeque<IntArray>()
            var answer = 0

            var d = 0
            var s = 0
            var nr = 0
            var nc = 0

            clouds.add(intArrayOf(n-2, 0))
            clouds.add(intArrayOf(n-2, 1))
            clouds.add(intArrayOf(n-1, 0))
            clouds.add(intArrayOf(n-1, 1))

            for(order in 0 until m) {
                d = input()
                s = input()

                for(i in clouds.indices) {
                    nr = (n * offset + clouds[i][0] + dr[d] * s) % n
                    nc = (n * offset + clouds[i][1] + dc[d] * s) % n
                    basket[nr][nc]++
                    clouds[i][0] = nr
                    clouds[i][1] = nc
                }

                val preCloud = Array(n) { BooleanArray(n) }
                for((r, c) in clouds) {
                    for (d in 2..8 step 2) {
                        nr = r + dr[d]
                        nc = c + dc[d]
                        if (nr !in 0 until n || nc !in 0 until n) continue
                        if (basket[nr][nc] > 0) basket[r][c]++
                    }

                    preCloud[r][c] = true
                }

                clouds.clear()
                for(r in 0 until n) for(c in 0 until n) {
                    if (preCloud[r][c]) continue
                    if (basket[r][c] >= 2) {
                        clouds.add(intArrayOf(r, c))
                        basket[r][c] -= 2
                    }
                }
            }

            for(r in 0 until n) for(c in 0 until n) answer += basket[r][c]
            print(answer)
        }
    }
}

fun main() {
    소병희_마법사상어와비바라기.solve()
}