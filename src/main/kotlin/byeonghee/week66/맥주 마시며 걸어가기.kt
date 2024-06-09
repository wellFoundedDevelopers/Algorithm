package byeonghee.week66

import kotlin.math.abs

class 소병희_맥주마시며걸어가기 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val t = readLine().toInt()
            val sb = StringBuilder()
            val q = ArrayDeque<IntArray>()

            repeat(t) {
                val n = readLine().toInt()
                val home = readLine().split(" ").let { intArrayOf(it[0].toInt(), it[1].toInt()) }
                val conv = Array(n) { IntArray(2) }
                val visited = BooleanArray(n)

                for(i in 0 until n) {
                    conv[i] = readLine().split(" ").let { intArrayOf(it[0].toInt(), it[1].toInt()) }
                }

                val festival = readLine().split(" ").let { intArrayOf(it[0].toInt(), it[1].toInt()) }

                q.add(intArrayOf(home[0], home[1]))
                var possible = false

                while(q.isNotEmpty()) {
                    val (x, y) = q.removeFirst()
                    if (abs(x - festival[0]) + abs(y - festival[1]) <= 1000) {
                        possible = true
                        break
                    }

                    for(i in 0 until n) {
                        if (visited[i]) continue

                        val dist = abs(x - conv[i][0]) + abs(y - conv[i][1])
                        if (dist > 1000) continue

                        visited[i] = true
                        q.add(intArrayOf(conv[i][0], conv[i][1]))
                    }
                }
                sb.appendLine(if (possible) "happy" else "sad")
            }

            print(sb)
        }
    }
}

fun main() {
    소병희_맥주마시며걸어가기.solve()
}