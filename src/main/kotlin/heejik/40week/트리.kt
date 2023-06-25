package heejik.`40week`

import kotlin.properties.Delegates

class 트리 {

    data class Node(
        val number: Int,
        val parentNumber: Int?
    )

    var n by Delegates.notNull<Int>()
    val nodes = mutableListOf<Node>()

    fun solve() {
        setting()
        deleteNode(readln().toInt())
        getLeafNodeCount().also {
            println(it)
        }
    }

    private fun setting() {
        n = readln().toInt()
        readln().split(' ').map { it.toInt() }.forEachIndexed { nodeNumber, parentNumber ->
            val node = if (parentNumber == -1) {
                Node(nodeNumber, null)
            } else {
                Node(nodeNumber, parentNumber)
            }
            nodes.add(node)
        }
    }

    private fun deleteNode(nodeNumber: Int) {
        val queue = ArrayDeque<Int>()
        queue.add(nodeNumber)
        nodes.removeAt(nodeNumber)

        while (queue.isNotEmpty()) {
            val deleteNode = queue.removeFirst()
            val removedNodes = nodes.filter { it.parentNumber == deleteNode }
            queue.addAll(removedNodes.map { it.number })
            nodes.removeAll(removedNodes)
        }
    }

    private fun getLeafNodeCount(): Int {
        val parentNodes = mutableSetOf<Int>()

        nodes.forEach {
            parentNodes.add(it.parentNumber ?: return@forEach)
        }

        return nodes.size - parentNodes.size
    }
}


fun main() {
    트리().solve()
}