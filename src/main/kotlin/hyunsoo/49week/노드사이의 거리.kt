package hyunsoo.`49week`

import java.util.*

/**
 *
 * <문제>
 * [노드사이의 거리](https://www.acmicpc.net/problem/1240)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_노드사이의_거리` {

    private data class Bundle(val node: Int, val cost: Int)

    private lateinit var treeInfo: Array<IntArray>

    fun solution() {

        val (nodeCnt, wantToKnowCnt) = readln().split(" ").map { it.toInt() }

        treeInfo = Array(nodeCnt + 1) {
            IntArray(nodeCnt + 1)
        }

        repeat(nodeCnt - 1) {
            val (start, end, cost) = readln().split(" ").map { it.toInt() }
            treeInfo[start][end] = cost
            treeInfo[end][start] = cost
        }

        repeat(wantToKnowCnt) {

            val (start, end) = readln().split(" ").map { it.toInt() }

            val visited = BooleanArray(nodeCnt + 1)

            val queue: Queue<Bundle> = LinkedList()

            treeInfo[start].forEachIndexed { nodeIndex, cost ->

                if (cost == NO_WAY) return@forEachIndexed

                visited[nodeIndex] = true
                queue.add(Bundle(nodeIndex, cost))

            }

            while (queue.isNotEmpty()) {

                val (node, preCost) = queue.poll()

                if (node == end) {
                    println(preCost)
                    return@repeat
                }

                treeInfo[node].forEachIndexed { nodeIndex, cost ->

                    if (cost == NO_WAY) return@forEachIndexed

                    if (visited[nodeIndex]) return@forEachIndexed

                    visited[nodeIndex] = true
                    queue.add(Bundle(nodeIndex, preCost + cost))

                }

            }

        }
    }

    companion object {
        const val NO_WAY = 0
    }
}

fun main() {
    전현수_노드사이의_거리().solution()
}