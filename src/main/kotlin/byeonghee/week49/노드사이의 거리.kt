package byeonghee.week49

class 소병희_노드사이의거리 {
    companion object {

        const val INF = 12_345_678
        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val adjMat = Array(n) { i -> IntArray(n) { j -> if (i == j) 0 else INF } }

            repeat(n-1) {
                val (a, b, w) = readLine().split(" ").map { it.toInt() }
                adjMat[a-1][b-1] = w
                adjMat[b-1][a-1] = w
            }

            for(k in 0 until n) {
                for(i in 0 until n) for(j in 0 until n) {
                    adjMat[i][j] = adjMat[i][j].coerceAtMost(adjMat[i][k] + adjMat[k][j])
                }
            }

            val sb = StringBuilder()
            repeat(m) {
                val (a, b) = readLine().split(" ").map { it.toInt() }
                sb.appendLine(adjMat[a-1][b-1])
            }
            print(sb)
        }
    }
}

fun main() {
    소병희_노드사이의거리.solve()
}