package byeonghee.week55

class `소병희_여행 가자` {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val m = readLine().toInt()
            val adj = Array(n) { BooleanArray(n) }
            val plan = HashSet<Int>(m)
            val visited = HashSet<Int>(n)
            val q = ArrayDeque<Int>()
            var cnt = 1

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    adj[i][j] = v == "1"
                    adj[j][i] = v == "1"
                }
            }

            readLine().split(" ").map { plan.add(it.toInt() - 1)}

            q.add(plan.first())
            visited.add(plan.first())

            while(q.isNotEmpty()) {
                val v = q.removeFirst()
                for(go in 0 until n) {
                    if (adj[v][go].not()) continue
                    if (visited.contains(go)) continue
                    q.add(go)
                    visited.add(go)
                    if (plan.contains(go)) cnt++
                }
            }

            if (cnt == plan.size) println("YES")
            else println("NO")
        }
    }
}

fun main() {
    `소병희_여행 가자`.solve()
}