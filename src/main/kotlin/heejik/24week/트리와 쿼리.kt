package heejik.`24week`

import kotlin.properties.Delegates

class `트리와 쿼리` {

    var n by Delegates.notNull<Int>()
    var r by Delegates.notNull<Int>()
    var q by Delegates.notNull<Int>()
    private lateinit var edges: MutableList<MutableList<Int>>
    private lateinit var children: MutableList<MutableList<Int>>
    private lateinit var subTreeSize: MutableList<Int>


    fun solve() {
        setting()
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            r = this[1]
            q = this[2]
        }
        edges = MutableList(n + 1) { mutableListOf() }
        children = MutableList(n + 1) { mutableListOf() }
        subTreeSize = MutableList(n + 1) { 0 }

        repeat(n - 1) {
            val (u, v) = readln().split(' ').map { it.toInt() }
            edges[u].add(v)
            edges[v].add(u)
        }

        makeTree(currentNode = r, parent = -1)
        countSubtreeNode(currentNode = r)

        repeat(q) {
            println(subTreeSize[readln().toInt()])
        }
    }

    private fun makeTree(currentNode: Int, parent: Int) {
        for (node in edges[currentNode]) {
            if (node == parent) continue
            children[currentNode].add(node)
            makeTree(node, currentNode)
        }
    }

    private fun countSubtreeNode(currentNode: Int) {
        subTreeSize[currentNode] = 1
        for (node in children[currentNode]) {
            countSubtreeNode(node)
            subTreeSize[currentNode] += subTreeSize[node]
        }
    }
}


fun main() {
    `트리와 쿼리`().solve()
}