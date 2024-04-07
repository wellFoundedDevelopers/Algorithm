package byeonghee.week56

class 소병희_톱니바퀴2 {
    companion object {
        const val R = 2
        const val L = 6
        const val MOD = 8
        fun solve() = with(System.`in`.bufferedReader()) {
            val t = readLine().toInt()
            val wheels = Array(t) { IntArray(8) }
            val dirs = IntArray(t)

            repeat(t) { i ->
                readLine().forEachIndexed { j, c -> wheels[i][j] = c.digitToInt() }
            }

            val k = readLine().toInt()
            repeat(k) {
                val (i, d) = readLine().split(" ").map { it.toInt() }
                var ld = (MOD + d) % MOD
                var rd = (MOD + d) % MOD
                var stop = false

                for(l in i-2 downTo 0) {
                    if (wheels[l][(R + 8 - dirs[l]) % MOD] == wheels[l+1][(L + 8 - dirs[l+1]) % MOD]) {
                        stop = true
                    }
                    dirs[l+1] = (dirs[l+1] + ld) % MOD
                    ld = (MOD - ld) % MOD
                    if (stop) break
                    if (l == 0) dirs[l] = (dirs[l] + ld) % MOD
                }

                stop = false
                if (i in 2 until t) dirs[i-1] = (dirs[i-1] - rd + MOD) % MOD
                for(r in i until t) {
                    if (wheels[r-1][(R + 8 -  dirs[r-1]) % MOD] == wheels[r][(L + 8 - dirs[r]) % MOD]) {
                        stop = true
                    }
                    dirs[r-1] = (dirs[r-1] + rd) % MOD
                    rd = (MOD - rd) % MOD
                    if (stop) break
                    if (r == t-1) dirs[r] = (dirs[r] + rd) % MOD
                }
            }

            var answer = 0
            for(i in 0 until t) {
                if (wheels[i][(8 - dirs[i]) % MOD] == 1) answer++
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_톱니바퀴2.solve()
}