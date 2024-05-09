package hyunsoo.`62week`

/**
 *
 * <문제>
 * [최소 스패닝 트리](https://www.acmicpc.net/problem/1197)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_최소_스패닝_트리` {

    private data class NodeInfo(val start: Int, val end: Int, val cost: Int)

    fun solution() {

        var sum = 0
        val nodeInfoList = mutableListOf<NodeInfo>()
        val (v, e) = readln().split(" ").map { it.toInt() }
        val parentInfo = IntArray(v + 1) {
            it
        }

        repeat(e) {
            val (start, end, cost) = readln().split(" ").map { it.toInt() }
            nodeInfoList.add(NodeInfo(start, end, cost))
        }

        nodeInfoList.sortedBy { it.cost }.forEach {

            val (start, end, cost) = it
            if (start.hasSameParent(end, parentInfo)) return@forEach

            sum += cost
            union(start, end, parentInfo)
        }

        println(sum)
    }

    private fun findParent(target: Int, parentInfo: IntArray): Int {
        if (target == parentInfo[target]) return target
        return findParent(parentInfo[target], parentInfo)
    }

    private fun Int.hasSameParent(other: Int, parentInfo: IntArray) =
        findParent(this, parentInfo) == findParent(other, parentInfo)

    private fun union(a: Int, b: Int, parentInfo: IntArray) {
        val aParent = findParent(a, parentInfo)
        val bParent = findParent(b, parentInfo)

        if (aParent < bParent) parentInfo[bParent] = aParent
        else parentInfo[aParent] = bParent
    }
}

fun main() {
    전현수_최소_스패닝_트리().solution()
}