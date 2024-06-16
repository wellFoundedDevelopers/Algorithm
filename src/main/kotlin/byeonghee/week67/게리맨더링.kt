package byeonghee.week67

import kotlin.math.abs

class 소병희_게리맨더링 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val popul = IntArray(n)
            var sum = 0
            var answer = 0

            readLine().split(" ").forEachIndexed { i, v ->
                popul[i] = v.toInt().also { sum += it }
            }

            val adjList = Array(n) { ArrayDeque<Int>() }

            repeat(n) { i ->
                readLine().split(" ").drop(1).forEach { j ->
                    adjList[i].add(j.toInt() - 1)
                }
            }

            fun checkAdj(visited: IntArray): Boolean {
                val q = ArrayDeque<Int>()
                val nxt = visited.indexOfFirst { it != 0 }
                if (nxt == -1) return false

                q.add(nxt)
                visited[nxt] = 1

                while(q.isNotEmpty()) {
                    val top = q.removeFirst()
                    for(i in adjList[top]) {
                        if (visited[i] == 0 || visited[i] == 1) continue
                        q.add(i)
                        visited[i] = 1
                    }
                }

                return visited.all { it == 0 || it == 1 }
            }

            fun dfs(cur: Int, visited: IntArray, part: Int) {
                if (answer > abs(sum - 2 * part)) {
                    if (checkAdj(visited.clone())) {
                        answer = abs(sum - 2 * part)
                    }
                }

                for(nxt in adjList[cur]) {
                    if (visited[nxt] == visited[cur]) continue

                    var tmp = visited[nxt]
                    visited[nxt] = visited[cur]
                    dfs(nxt, visited, part + popul[nxt])
                    visited[nxt] = tmp
                }
            }

            answer = sum
            val visited = IntArray(n) { it }
            dfs(0, visited, popul[0])

            if (answer == sum) println(-1)
            else println(answer)
        }

    }
}

fun main() {
    소병희_게리맨더링.solve()
}
