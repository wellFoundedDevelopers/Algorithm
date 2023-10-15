package byeonghee.week48

import java.util.*

class 소병희_백도어 {

    companion object {
        const val INF = 99_999_999_999

        fun solve()= with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val hideable = readLine().split(" ").map { it == "0" }.toBooleanArray()
            val adj = Array(n) { ArrayDeque<Pair<Int, Long>>() }

            repeat(m) {
                val (a, b, t) = readLine().split(" ").map { it.toInt() }
                adj[a].add(b to t.toLong())
                adj[b].add(a to t.toLong())
            }

            val visited = LongArray(n) { INF }
            val q = PriorityQueue<Pair<Int, Long>> { a, b -> (if (a.second - b.second > 0) 1 else -1) }
            val GOAL = n-1
            var ans = -1L

            visited[0] = 0
            for((v, e) in adj[0]) {
                if (hideable[v]) {
                    q.add(v to e)
                    visited[v] = e
                }
            }

            while(q.isNotEmpty()) {
                val (pos, curDist) = q.poll()

                if (pos == GOAL) {
                    ans = curDist
                    break
                }

                if (visited[pos] < curDist) continue

                for((v, e) in adj[pos]) {
                    if ((v == GOAL || hideable[v]) && curDist + e < visited[v]) {
                        q.add(v to curDist + e)
                        visited[v] = curDist + e
                    }
                }
            }

            println(ans)
        }
    }
}