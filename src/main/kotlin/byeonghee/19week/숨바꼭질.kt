package byeonghee.`19week`

import java.io.*

class 소병희_숨바꼭질 {

    companion object {
        data class Pos(val p: Int, val t: Int)

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, k) = readLine().split(" ").map { it.toInt() }

            if (n >= k) {
                println(n - k)
                return
            }

            val visited = BooleanArray(k * 2)
            val q = ArrayDeque<Pos>()

            q.addLast(Pos(n, 0))
            while(q.isNotEmpty()) {
                val (p, t) = q.removeFirst()
                if (p == k) {
                    println(t)
                    return
                }
                if (p !in 0  until  k * 2 || visited[p]) continue

                visited[p] = true
                q.addLast(Pos(p + 1, t + 1))
                q.addLast(Pos(p - 1, t + 1))
                q.addLast(Pos(p * 2, t + 1))
            }
        }
    }
}

fun main() {
    소병희_숨바꼭질.solve()
}