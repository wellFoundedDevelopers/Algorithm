package byeonghee.week48

import java.util.*

class 소병희_백도어 {

    companion object {
        const val INF = Long.MAX_VALUE

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
            val q = PriorityQueue<Pair<Int, Long>> { a, b -> (a.second - b.second).toInt() }
            val GOAL = n-1

            visited[0] = 0L
            q.add(0 to 0L)

            while(q.isNotEmpty()) {
                val (pos, curDist) = q.poll()

                if (visited[pos] < curDist) continue
                visited[pos] = curDist

                for((v, e) in adj[pos]) {
                    if (v != GOAL && hideable[v].not()) continue
                    if (curDist + e < visited[v]) {
                        visited[v] = curDist + e
                        q.add(v to visited[v])
                    }
                }
            }

            println(if (visited[GOAL] == INF) -1 else visited[GOAL])
        }
    }
}

fun main() {
    소병희_백도어.solve()
}