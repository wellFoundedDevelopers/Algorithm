package byeonghee.week66

class 소병희_순회강연 {
    companion object {
        const val PAY = 0
        const val DUE = 1

        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val requests = Array(n) { IntArray(2) }
            var answer = 0

            repeat(n) { i ->
                readLine().split(" ").let {
                    requests[i] = intArrayOf(it[PAY].toInt(), it[DUE].toInt())
                }
            }

            requests.sortWith(compareByDescending<IntArray> { it[PAY] }.thenBy { it[DUE] })

            var occ = BooleanArray(10001)
            var full = 0
            for(i in 0 until n) {
                var p = requests[i][DUE]

                if (full >= p) continue

                while(p > 0 && occ[p]) {
                    p--
                }

                if (p > 0) {
                    occ[p] = true
                    answer += requests[i][PAY]
                }
                else {
                    full = requests[i][DUE]
                }
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_순회강연.solve()
}