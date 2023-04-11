package byeonghee.week31

import java.io.StreamTokenizer

class 소병희_운동 {

    companion object {
        const val MAX = Int.MAX_VALUE / 2

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            val v = input()
            val e = input()
            val adjMatrix = Array(v) { IntArray(v) { MAX } }
            var answer = MAX

            for(i in 0 until e) {
                val from = input() - 1
                val to = input() - 1
                val dist = input()
                adjMatrix[from][to] = dist
            }

            for(k in 0 until v) for(i in 0 until v) for(j in 0 until v) {
                adjMatrix[i][j] = adjMatrix[i][j].coerceAtMost(adjMatrix[i][k] + adjMatrix[k][j])
            }

            for(r in 0 until v) {
                answer = answer.coerceAtMost(adjMatrix[r][r])
            }

            print(if (answer == MAX) -1 else answer)
        }

        fun StreamTokenizer.input(): Int {
            nextToken()
            return nval.toInt()
        }
    }
}

fun main() {
    소병희_운동.solve()
}