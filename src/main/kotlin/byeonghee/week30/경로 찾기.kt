package byeonghee.week30

import java.io.StreamTokenizer

class `소병희_경로 찾기` {

    companion object {
        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val adjMatrix = Array(n) { IntArray(n) }
            val adjList = Array(n) { ArrayDeque<Int>(n) }
            val sb = StringBuilder()

            for(i in 0 until n) for(j in 0 until n) {
                adjMatrix[i][j] = input()
                if (adjMatrix[i][j] == 1) adjList[i].add(j)
            }

            for(i in 0 until n) {
                for(j in adjList[i]) {
                    for(k in adjList[j]) {
                        if (adjMatrix[i][k] == 1) continue
                        adjMatrix[i][k] = 1
                        adjList[i].add(k)
                    }
                }

                sb.appendLine(adjMatrix[i].joinToString(" "))
            }

            print(sb)
        }
    }
}

fun main() {
    `소병희_경로 찾기`.solve()
}