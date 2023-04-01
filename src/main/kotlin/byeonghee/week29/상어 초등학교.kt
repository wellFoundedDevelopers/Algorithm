package byeonghee.week29

import java.io.StreamTokenizer

class 소병희_상어초등학교 {

    companion object {
        const val R = 0
        const val C = 1
        const val F = 2
        const val S = 3

        enum class Mode {
            COUNT_FRIEND, REDUCE_SPACE
        }

        val dr = intArrayOf(-1, 0, 1, 0)
        val dc = intArrayOf(0, 1, 0, -1)
        val score = intArrayOf(0, 1, 10, 100, 1000)

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val bf = Array(n*n + 1) { IntArray(4) }
            val room = Array(n) { IntArray(n) }
            val space = Array(n) { IntArray(n) { 4 } }
            val prev = IntArray(4)
            var answer = 0
            var stu = 0
            var f = 0

            fun lookAround(r: Int, c: Int, mode: Mode): Int {
                var nf = 0
                var nr = 0
                var nc = 0

                for(d in 0 until 4) {
                    nr = r + dr[d]
                    nc = c + dc[d]
                    if (nr !in 0 until n || nc !in 0 until n) continue

                    when(mode) {
                        Mode.COUNT_FRIEND -> if (room[nr][nc] in bf[stu]) nf++
                        Mode.REDUCE_SPACE -> space[nr][nc]--
                    }
                }
                return nf
            }

            for(i in 0 until n) {
                space[0][i]--
                space[n-1][i]--
                space[i][0]--
                space[i][n-1]--
            }

            for(i in 0 until n*n) {
                stu = input()
                for(j in 0 until 4) {
                    bf[stu][j] = input()
                }

                prev.fill(-1)
                search@ for(r in 0 until n) for(c in 0 until n) {
                    if (room[r][c] > 0) continue

                    f = lookAround(r, c, Mode.COUNT_FRIEND)
                    if (f > prev[F] || (f == prev[F] && space[r][c] > prev[S])) {
                        prev[R] = r
                        prev[C] = c
                        prev[F] = f
                        prev[S] = space[r][c]
                    }
                }

                val (r, c) = prev
                room[r][c] = stu
                lookAround(r, c, Mode.REDUCE_SPACE)
            }

            for(r in 0 until n) for(c in 0 until n) {
                stu = room[r][c]
                answer += score[lookAround(r, c, Mode.COUNT_FRIEND)]
            }
            print(answer)
        }
    }
}

fun main() {
    소병희_상어초등학교.solve()
}