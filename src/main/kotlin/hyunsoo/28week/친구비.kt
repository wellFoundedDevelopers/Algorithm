package hyunsoo.`28week`

/**
 *
 * <문제>
 * [친구비](https://www.acmicpc.net/problem/16562)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 * 친구 관계가 생기면 무조건 부모의 최소값으로만 비교해서 합집합 연산으로 하도록 하였음.
 * - 부모의 비용이 동일하다면 합집합이 안되는 이슈
 *   - 동일하다면 부모의 인덱스로 합집합 연산을 하도록 변경
 *
 */
class `전현수_친구비` {

    private lateinit var parent: IntArray
    private lateinit var costs: List<Int>

    fun solution() {
        val (n, m, k) = readln().split(" ").map { it.toInt() }

        parent = IntArray(n + 1) { it }
        costs = listOf(0) + readln().split(" ").map { it.toInt() }
        val paidInfo = BooleanArray(n + 1)

        repeat(m) {
            val (a, b) = readln().split(" ").map { it.toInt() }
            union(a, b)
        }

        var needCash = 0

        parent.forEach {

            val parent = getParent(it)

            if (paidInfo[parent].not()) {
                needCash += costs[parent]
                paidInfo[parent] = true
            }

        }

        (k - needCash).apply {
            if (this < 0) println("Oh no")
            else println(needCash)
        }

    }

    private fun getParent(target: Int): Int {
        return if (parent[target] == target) target
        else getParent(parent[target])
    }

    private fun union(a: Int, b: Int) {
        val aParent = getParent(a)
        val bParent = getParent(b)

        val aParentCost = costs[aParent]
        val bParentCost = costs[bParent]

        if (bParentCost < aParentCost) parent[aParent] = bParent
        else if (aParentCost < bParentCost) parent[bParent] = aParent
        else {
            if (bParent < aParent) parent[aParent] = bParent
            else parent[bParent] = aParent
        }
    }

}

fun main() {
    전현수_친구비().solution()
}