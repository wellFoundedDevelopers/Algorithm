package byeonghee.week60

class 소병희_뮤탈리스크 {
    companion object {
        val pool = arrayOf(
                intArrayOf(9, 3, 1),
                intArrayOf(9, 1, 3),
                intArrayOf(3, 9, 1),
                intArrayOf(3, 1, 9),
                intArrayOf(1, 9, 3),
                intArrayOf(1, 3, 9)
        )

        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val scv = IntArray(3)

            readLine().split(" ").forEachIndexed { i, v ->
                scv[i] = v.toInt()
            }

            val record = Array(scv[0]+1) { Array(scv[1]+1) { IntArray(scv[2]+1) }}
            val q = ArrayDeque<IntArray>()

            q.add(intArrayOf(0, 0, 0, 0))
            for(turn in 1 .. 60) {
                while(true) {
                    val (scv1, scv2, scv3, t) = q.first()
                    if (t == turn) break
                    else q.removeFirst()

                    for((at1, at2, at3) in pool) {
                        val cur0 = minOf(scv1 + at1, scv[0])
                        val cur1 = minOf(scv2 + at2, scv[1])
                        val cur2 = minOf(scv3 + at3, scv[2])

                        if (cur0 == scv[0] && cur1 == scv[1] && cur2 == scv[2]) {
                            println(turn)
                            return@with
                        }

                        if (record[cur0][cur1][cur2] > 0) continue
                        record[cur0][cur1][cur2] = turn
                        q.add(intArrayOf(cur0, cur1, cur2, turn))
                    }
                }
            }
        }
    }
}

fun main() {
    소병희_뮤탈리스크.solve()
}