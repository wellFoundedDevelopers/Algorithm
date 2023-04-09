package heejik.`30week`

import kotlin.properties.Delegates

class `경로 찾기` {

    var n by Delegates.notNull<Int>()
    val node = mutableMapOf<Int, MutableList<Int>>()

    fun solve() {
        setting()
        printAllRoute()
    }

    private fun setting() {
        n = readln().toInt()
        repeat(n) { key ->
            node[key] = mutableListOf()
            readln().split(' ').map { it.toInt() }.forEachIndexed { index, i ->
                if (i == 1) {
                    node[key]!!.add(index)
                }
            }
        }
    }

    private fun printAllRoute() {
        repeat(n) { key ->
            repeat(n) { findNode ->
                print("${if (findRoute(key, findNode)) 1 else 0} ")
            }
            println()
        }
    }

    private fun findRoute(key: Int, findNode: Int): Boolean {
        val visited = BooleanArray(size = n)
        val queue = ArrayDeque<Int>()
        queue.addAll(node[key]!!)
        while (queue.isNotEmpty()) {
            val tmpNode = queue.removeFirst()
            if (tmpNode == findNode) return true
            val canGoNodes = node[tmpNode]!!.filter { visited[it].not() }
            queue.addAll(canGoNodes)
            canGoNodes.forEach {
                visited[it] = true
            }
        }
        return false
    }
}

fun main() {
    `경로 찾기`().solve()
}