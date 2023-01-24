package hyunsoo.`20week`

/**
 *
 * <문제>
 * [최소 스패닝 트리](https://www.acmicpc.net/problem/1197)
 *
 * - 아이디어
 * 크루스칼 연습 드가자
 *
 * - 트러블 슈팅
 *
 */
class `전현수_최소_스패닝_트리` {

    data class CostData(
        val a: Int,
        val b: Int,
        val cost: Int,
    )

    private val costTable = mutableListOf<CostData>()
    private var leastCost = 0

    fun solution() {

        val (vCnt, eCnt) = readln().split(" ").map { it.toInt() }
        val parent = IntArray(vCnt + 1) { it }

        repeat(eCnt) {
            readln().split(" ").map { it.toInt() }.apply {
                costTable.add(
                    CostData(
                        this.first(),
                        this[1],
                        this.last(),
                    )
                )
            }
        }

        costTable.sortedBy { it.cost }
            .forEach {

                val (a, b, cost) = it

                val aParent = findParent(parent, a)
                val bParent = findParent(parent, b)

                if (aParent != bParent) {
                    union(parent, aParent, bParent)
                    leastCost += cost
                }

            }

        println(leastCost)

    }


    private fun findParent(parent: IntArray, target: Int): Int {
        return if (parent[target] == target) return target
        else findParent(parent, parent[target])
    }

    private fun union(parent: IntArray, aParent: Int, bParent: Int) {
        if (aParent < bParent) {
            parent[bParent] = aParent
        } else {
            parent[aParent] = bParent
        }
    }
}

fun main() {
    전현수_최소_스패닝_트리().solution()
}