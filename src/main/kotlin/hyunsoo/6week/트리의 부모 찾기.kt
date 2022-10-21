package hyunsoo.`6week`

import java.util.*

/**
 * <문제>
 * [트리의 부모 찾기](https://www.acmicpc.net/problem/11725)
 *
 * 루트없는 트리가 주어짐.
 * 트리의 루트는 1. 각 노드의 부모를 구하라.
 */

val rootQueue: Queue<Int> = ArrayDeque()
val nodeCnt = readln().toInt()
val treeArray = Array(nodeCnt + 1) { mutableListOf<Int>() }
val parentNodeData = IntArray(nodeCnt + 1)

// 루트는 1
val initParent = 1

fun main() {


    repeat(nodeCnt - 1) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        treeArray[a].add(b)
        treeArray[b].add(a)
    }
    findParentNode()
    parentNodeData.drop(2).forEach {
        println(it)
    }

}

fun findParentNode() {

    rootQueue.add(initParent)

    while (rootQueue.isNotEmpty()) {
        val curParent = rootQueue.poll()
        // 부모와 연결되어있는 것들 자식으로 판정
        treeArray[curParent].forEach { child ->
            parentNodeData[child] = curParent
            // 방문 판정 -> 자식 노드에서 부모 노드 제거
            treeArray[child].remove(curParent)
            rootQueue.add(child)
        }
    }

}