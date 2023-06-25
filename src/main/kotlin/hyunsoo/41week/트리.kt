package hyunsoo.`41week`

import java.util.LinkedList
import java.util.Queue
import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [트리](https://www.acmicpc.net/problem/1068)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_트리` {

    private lateinit var root: Node

    private data class Node(
        val num: Int,
        val children: MutableList<Node> = mutableListOf(),
    ) {

        fun find(target: Int): Node? {

            if (this.num == target) return this

            if (this.children.isEmpty()) return null

            val findFromChildren =
                children.find { it.num == target }

            return findFromChildren ?: children.firstNotNullOf { it.find(target) }

        }

        fun findParent(target: Int): Node? {

            if (this.children.isEmpty()) return null

            if (children.find { it.num == target } != null) return this

            return children.firstNotNullOf { it.findParent(target) }

        }

        fun countLeafNode(): Int {

            if (this.children.isEmpty()) return 1

            return this.children.sumOf {
                it.countLeafNode()
            }

        }
    }

    private data class OrphanageNode(
        val parentNum: Int,
        val self: Node,
    )

    fun solution() {

        val nodeCnt = readln().toInt()

        val orphanageNodeList: Queue<OrphanageNode> = LinkedList()
        val noneOrphanageNodeInfo = BooleanArray(51)

        val nodeInfo = readln().split(" ")

        val deleteIndex = readln().toInt()

        nodeInfo.map { it.toInt() }
            .forEachIndexed { index, parentNum ->

                if (parentNum == -1) {
                    root = Node(index)
                    noneOrphanageNodeInfo[index] = true
                    return@forEachIndexed
                }

                orphanageNodeList.add(OrphanageNode(parentNum, Node(index)))

            }

        while (orphanageNodeList.isNotEmpty()) {

            val orphanageNode = orphanageNodeList.poll()
            val (parentNum, node) = orphanageNode

            if (noneOrphanageNodeInfo[parentNum]) {

                // 루트의 자식일 경우
                if (parentNum == root.num) {
                    root.children.add(node)
                    noneOrphanageNodeInfo[node.num] = true
                } else {
                    val parent = requireNotNull(root.find(parentNum))
                    parent.children.add(node)
                    noneOrphanageNodeInfo[node.num] = true
                }

            } else {
                orphanageNodeList.add(orphanageNode)
            }
        }

        if (deleteIndex == root.num) {
            println(0)
            exitProcess(0)
        }

        val parent = requireNotNull(root.findParent(deleteIndex))
        parent.children.removeAll { it.num == deleteIndex }
        println(root.countLeafNode())

    }

}

fun main() {
    전현수_트리().solution()
}