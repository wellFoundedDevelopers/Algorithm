package byeonghee.week47

class 소병희_회장뽑기 {

    companion object {
        const val INF = 987_654_321

        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val adj = Array(n) { i -> IntArray(n) { j -> if (i == j) 0 else INF }}

            var a = 0
            var b = 0

            readLine().split(" "). let {
                a = it[0].toInt()
                b = it[1].toInt()
            }

            while(a != -1) {
                adj[a-1][b-1] = 1
                adj[b-1][a-1] = 1

                readLine().split(" "). let {
                    a = it[0].toInt()
                    b = it[1].toInt()
                }
            }

            for(k in 0 until n) {
                for(i in 0 until n) for(j in 0 until n) {
                    val before = adj[i][j]
                    val detour = adj[i][k] + adj[k][j]
                    if (detour < before) adj[i][j] = detour
                }
            }

            var minScore = 987_654_321
            val candidates = ArrayDeque<Int>()

            for(i in 0 until n) {
                val score = adj[i].maxOf{it}
                if (score < minScore) {
                    minScore = score
                    candidates.clear()
                    candidates.add(i + 1)
                }
                else if (score == minScore) {
                    candidates.add(i + 1)
                }
            }

            println("$minScore ${candidates.size}")
            println(candidates.joinToString(" "))
        }
    }
}

fun main() {
    소병희_회장뽑기.solve()
}