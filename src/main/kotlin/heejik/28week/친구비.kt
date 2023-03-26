package heejik.`28week`

import kotlin.math.min
import kotlin.properties.Delegates

class 친구비 {

    private var n by Delegates.notNull<Int>()
    private var m by Delegates.notNull<Int>()
    private var k by Delegates.notNull<Int>()
    private lateinit var cost: MutableList<Int>
    private lateinit var parent: MutableList<Int>

    fun solve() {
        setting()
        printAnswer()
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
            k = this[2]
        }
        cost = readln().split(' ').map { it.toInt() }.toMutableList()
        parent = MutableList(n) { it }

        repeat(m) {
            val (v, w) = readln().split(' ').map { it.toInt() }.sorted()
            union(v - 1, w - 1)
        }
    }

    private fun printAnswer() {
        var answer = 0

        parent.map { find(it) }.toSet().forEach {
            answer += cost[it]
        }
        println(if (answer > k) "Oh no" else answer)
    }

    private fun find(v: Int): Int {
        if (parent[v] == v) return v
        return find(parent[v])
    }

    private fun union(v: Int, w: Int) {
        val p1 = find(v)
        val p2 = find(w)

        parent[p2] = p1
        val minCost = min(cost[p2], cost[p1])
        cost[p1] = minCost
    }
}


fun main() {
    친구비().solve()
}