package jimin.`60week`

import java.lang.Integer.max

class `트리의 지름` {
    var result = 0
    fun solve() {
        val n = readln().toInt()
        val tree = MutableList<MutableList<Pair<Int, Int>>>(n + 1) { mutableListOf() }

        repeat(n - 1) {
            val (p, c, w) = readln().split(" ").map { it.toInt() }
            tree[p].add(Pair(c, w))
        }

        dfs(1, tree)

        println(result)
    }

    fun dfs(node: Int, tree: MutableList<MutableList<Pair<Int, Int>>>): Int {
        val maxi = mutableListOf<Int>(0)
        tree[node].forEach {
            val newNode = it.first
            maxi.add(it.second + dfs(newNode, tree))
        }
        maxi.sortDescending()
        if (maxi.size >= 2) {
            result = max(result, maxi[0] + maxi[1])
        }

        return maxi.first()
    }
}

fun main() {
    `트리의 지름`().solve()
}