package hyunsoo.`63week`

/**
 *
 * <문제>
 * [도시 건설](https://www.acmicpc.net/problem/21924)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_도시_건설` {

    private data class RoadInfo(val a: Int, val b: Int, val cost: Long)

    fun solution() {

        val (n, m) = readln().split(" ").map { it.toInt() }
        val parentInfo = IntArray(n + 1) {
            it
        }
        val roadInfoList = mutableListOf<RoadInfo>()

        var sum = 0L

        repeat(m) {
            readln().split(" ").apply {
                roadInfoList.add(
                    RoadInfo(this[0].toInt(), this[1].toInt(), this[2].toLong())
                )
            }
        }

        roadInfoList.sortedBy { it.cost }.forEach {

            val (a, b, c) = it
            if (a.hasSameParent(b, parentInfo)) return@forEach

            sum += c
            union(a, b, parentInfo)
        }

        if (parentInfo.isValid()) {
            println(roadInfoList.sumOf { it.cost } - sum)
        } else {
            println(-1)
        }

    }

    private fun IntArray.isValid(): Boolean {
        for (i in 1 until this.size) {
            if (findParent(i, this) != 1) return false
        }
        return true
    }

    private fun findParent(target: Int, parentInfo: IntArray): Int {
        return if (target == parentInfo[target]) target
        else findParent(parentInfo[target], parentInfo)
    }

    private fun Int.hasSameParent(other: Int, parentInfo: IntArray): Boolean {
        return findParent(this, parentInfo) == findParent(other, parentInfo)
    }

    private fun union(a: Int, b: Int, parentInfo: IntArray) {

        val aParent = findParent(a, parentInfo)
        val bParent = findParent(b, parentInfo)

        if (aParent < bParent) parentInfo[bParent] = aParent
        else parentInfo[aParent] = bParent
    }
}

fun main() {
    전현수_도시_건설().solution()
}