package byeonghee.week53

class 소병희_키순서 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val adj = Array(n) { IntArray(n) }

            repeat(m) {
                val (low, high) = readLine().split(" ").map { it.toInt() - 1 }
                adj[low][high] = 1
                adj[high][low] = -1
            }

            for(mid in 0 until n) for(a in 0 until n) for(b in 0 until n) {
                val compare = adj[a][mid] + adj[mid][b]
                when (compare) {
                    2 -> {
                        adj[a][b] = 1
                        adj[b][a] = -1
                    }

                    -2 -> {
                        adj[a][b] = -1
                        adj[b][a] = 1
                    }
                }
            }

            var answer = 0
            for(a in 0 until n) {
                var cnt = 1
                for(b in 0 until n) {
                    if (adj[a][b] != 0) cnt++
                }
                if (cnt == n) answer++
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_키순서.solve()
}