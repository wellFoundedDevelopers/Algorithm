package jimin.`41week`

/*
<문제>
[트리](https://www.acmicpc.net/problem/1068)

<구현 방법>
루트 노드를 구해서 재귀를 돌면서 제거할 노드를 발견하면
만약 형제가 있을 경우 그냥 두고,
없으면 leaf를 +1 해준다.
재귀를 끝까지 돌아서 자식이 없으면서 제거할 노드가 아니면 +1 해주었다.

<트러블 슈팅>
루트가 무조건 0이 아님!!

*/

class 트리 {
    private var leafNum = 0
    private var rootNode = -1

    fun solve() {
        val n = readln().toInt()
        val nodes = readln().split(" ").map { it.toInt() }
        val removeNode = readln().toInt()
        val tree = MutableList<MutableList<Int>>(n) { mutableListOf() }

        for (i in 0 until n) {
            if (nodes[i] != -1) {
                tree[nodes[i]].add(i)
            } else {
                rootNode = i
            }
        }

        dfs(-1, rootNode, removeNode, tree)
        println(leafNum)
    }

    fun dfs(parent: Int, now: Int, removeNode: Int, tree: MutableList<MutableList<Int>>) {
        val children = tree[now]
        if (now == removeNode) {
            if(parent != -1 && tree[parent].size == 1) {
                leafNum += 1
            }
            return@dfs
        }
        if (children.isEmpty()) {
            leafNum += 1
        }

        children.forEach {
            dfs(now, it, removeNode, tree)
        }
    }
}

fun main() {
    트리().solve()
}