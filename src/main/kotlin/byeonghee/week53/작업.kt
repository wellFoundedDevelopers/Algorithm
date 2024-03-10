package byeonghee.week53

class 소병희_작업 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val preJob = Array(n + 1) { ArrayDeque<Int>() }

            repeat(m) {
                val (pre, cur) = readLine().split(" ").map { it.toInt() }
                preJob[cur].add(pre)
            }

            val x = readLine().toInt()
            var answer = 0
            val visited = BooleanArray(n+1)
            val q = ArrayDeque<Int>(x)
            q.add(x)
            visited[x] = true

            while(q.isNotEmpty()) {
                val cur = q.removeFirst()
                preJob[cur].forEach {
                    if (visited[it].not()) {
                        q.add(it)
                        visited[it] = true
                        answer++
                    }
                }
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_작업.solve()
}