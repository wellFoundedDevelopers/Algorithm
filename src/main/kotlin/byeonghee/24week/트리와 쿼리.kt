package byeonghee.`24week`

import java.io.*


class 소병희_트리와쿼리 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {

            val (n, r, q) = readLine().split(" ").map { it.toInt() }
            val adjacentList = Array(n + 1) { mutableListOf<Int>() }
            val sizeList = IntArray(n + 1) { 1 }
            val visited = BooleanArray(n + 1)

            repeat(n - 1) {
                readLine().split(" ").let {
                    adjacentList[it[0].toInt()].add(it[1].toInt())
                    adjacentList[it[1].toInt()].add(it[0].toInt())
                }
            }

            fun findSubtree(root: Int) {
                visited[root] = true
                for(v in adjacentList[root]) {
                    if (visited[v]) continue
                    findSubtree(v)
                    sizeList[root] += sizeList[v]
                }
            }

            findSubtree(r)

            // 출력이 많을 땐 무조건 스트링빌더 쓰기!!!!!!!
            val sb = StringBuilder()
            repeat(q) {
                sb.appendLine(sizeList[readLine().toInt()])
            }
            println(sb)
        }
    }
}

fun main() {
    소병희_트리와쿼리.solve()
}