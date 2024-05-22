package byeonghee.week63

class 소병희_도시건설 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val edges = Array(m) { IntArray(3) }
            val parents = IntArray(n+1) { it }
            var total = 0L
            var answer = 0L
            var bridge = 0

            repeat(m) { i ->
                readLine().split(" ").let {
                    edges[i][0] = it[0].toInt()
                    edges[i][1] = it[1].toInt()
                    edges[i][2] = it[2].toInt()
                }
                total += edges[i][2]
            }

            fun findParents(a: Int): Int {
                return if (a != parents[a]) findParents(parents[a]).also { parents[a] = it } else parents[a]
            }

            edges.sortBy { it[2] }
            for((a, b, v) in edges) {
                val parA = findParents(a)
                val parB = findParents(b)
                if (parA == parB) continue

                parents[parB] = parA
                answer += v
                bridge++

                if (bridge == n-1) break
            }

            println(if (bridge < n-1) -1 else total - answer)
        }
    }
}

fun main() {
    소병희_도시건설.solve()
}