package byeonghee.`17week`

import java.io.*

class 소병희_트리순회 {

    companion object {
        const val PRE = 0
        const val IN = 1
        const val POST = 2

        data class Node(val left: Char, val right: Char)

        val nodeInfo = Array(26) { Node('.', '.') }

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()

            repeat(n) {
                val node = readLine()
                nodeInfo[node[0] - 'A'] = Node(node[2], node[4])
            }

            for(i in 0..2)
                println(traverse(0, i))
        }

        fun traverse(i: Int, order: Int) : StringBuilder {
            val ret = StringBuilder()
            ret.append('A' + i)

            val left = if (nodeInfo[i].left == '.') "" else traverse(nodeInfo[i].left - 'A', order)
            val right = if (nodeInfo[i].right == '.') "" else traverse(nodeInfo[i].right - 'A', order)

            when(order) {
                PRE -> {
                    ret.append(left)
                    ret.append(right)
                }
                IN -> {
                    ret.append(right)
                    ret.insert(0, left)
                }
                POST -> {
                    ret.insert(0, right)
                    ret.insert(0, left)
                }
            }

            return ret
        }
    }
}

fun main() {
    소병희_트리순회.solve()
}