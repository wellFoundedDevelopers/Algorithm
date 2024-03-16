package byeonghee.week54

class 소병희_DFS와BFS {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m, v) = readLine().split(" ").map { it.toInt() }
            val adj = Array(n+1) { IntArray(n+1) }
            val sb = StringBuilder()

            repeat(m) {
                val (a, b) = readLine().split(" ").map { it.toInt() }
                adj[a][b] = 1
                adj[b][a] = 1
            }

            fun dfs(p: Int, visited: BooleanArray) {
                sb.append(p)
                sb.append(" ")

                for(i in 1 .. n) {
                    if (adj[p][i] == 1 && visited[i].not()) {
                        visited[i] = true
                        dfs(i, visited)
                    }
                }
            }

            fun bfs(_p: Int, visited: BooleanArray) {
                val q = ArrayDeque<Int>()
                q.add(_p)

                while(q.isNotEmpty()) {
                    val p = q.removeFirst()
                    sb.append(p)
                    sb.append(" ")
                    for(i in 1 .. n) {
                        if (adj[p][i] == 1 && visited[i].not()) {
                            visited[i] = true
                            q.add(i)
                        }
                    }
                }
            }

            dfs(v, BooleanArray(n+1).apply { this[v] = true })
            sb.appendLine()
            bfs(v, BooleanArray(n+1).apply { this[v] = true })

            println(sb)
        }
    }
}

fun main() {
    소병희_DFS와BFS.solve()
}