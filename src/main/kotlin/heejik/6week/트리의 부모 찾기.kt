package heejik.`6week`

class `트리의 부모 찾기` {

    var tree = listOf<MutableList<Int>>()
    var parentNode = mutableListOf<Int>()
    fun solve() {
        val n = readln().toInt()
        tree = List(n+1) { mutableListOf() }
        parentNode = MutableList(n+1) {-1}
        repeat(n-1) {
            val (node1, node2) = readln().split(' ').map { it.toInt() }
            tree[node1].add(node2)
            tree[node2].add(node1)
        }
        bfs(1)
        parentNode.drop(2).forEach {
            println(it)
        }
    }

    fun bfs(start: Int) {
        parentNode[start] = 0
        val queue = ArrayDeque<Pair<Int,Int>>()
        tree[start].forEach {
            queue.add(Pair(it,start))
        }
        while (queue.isNotEmpty()) {
            val (node, parent) = queue.removeFirst()
            parentNode[node] = parent
            tree[node].forEach {
                if (parentNode[it] == -1) {
                    queue.add(Pair(it,node))
                }
            }
        }
    }
}

fun main() {

    `트리의 부모 찾기`().solve()
}