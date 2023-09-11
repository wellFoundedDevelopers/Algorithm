package byeonghee.week44

class 소병희_케빈베이컨의6단계법칙 {

    companion object {
        const val INF = 9_999

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val adj = Array(n+1) { IntArray(n+1) { INF } }
            var bacon = INF
            var ans = 0

            repeat(n) { i -> adj[i+1][i+1] = 0 }
            repeat(m) {
                val (a, b) = readLine().split(" ").map { it.toInt() }
                adj[a][b] = 1
                adj[b][a] = 1
            }

            for(k in 1..n) for(i in 1 .. n) for(j in 1 .. n) {
                adj[i][j] = minOf(adj[i][k] + adj[k][j], adj[i][j])
            }

            for(i in 1 .. n) {
                adj[i][0] = 0
                for(j in 1 .. n) adj[i][0] += adj[i][j]
                if (bacon > adj[i][0]) {
                    bacon = adj[i][0]
                    ans = i
                }
            }

            println(ans)
        }
    }
}

fun main() {
    소병희_케빈베이컨의6단계법칙.solve()
}