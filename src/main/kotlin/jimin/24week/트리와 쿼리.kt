package jimin.`24week`

/*
<문제>
[트리와 쿼리](https://www.acmicpc.net/problem/15681)

<구현 방법>
메모이제이션 이용!
treeInfo를 n + 1 크기만큼 만들고 초기 값을 1로 해주었다.
dfs로 돌면서 돌고난후 자식들의 treeInfo를 더해주는 식으로 했다

<트러블 슈팅>
역대급 시간초과!!!
입력도 바꿔보고 출력도 바꿔보았지만 안됨.
visited에 add하는 식으로 추가해줬는데 그렇게 하면 시간초과 난다!
visited를 정적 크기로 바꾸고 boolean으로 바꿔주니 해결됨!
*/

import java.io.BufferedReader
import java.io.InputStreamReader

class `트리와 쿼리` {
    lateinit var tree: MutableList<MutableList<Int>>
    lateinit var treeInfo: MutableList<Int>
    lateinit var visited: MutableList<Boolean>

    fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
        val (n, r, q) = readLine().split(" ").map { it.toInt() }
        tree = MutableList(n + 1) { mutableListOf() }
        treeInfo = MutableList(n + 1) { 1 }
        visited = MutableList(n + 1) { false }

        repeat(n - 1) {
            readLine().split(" ").map { it.toInt() }.apply {
                tree[first()].add(last())
                tree[last()].add(first())
            }
        }
        visited[r] = true
        dfs(r)
        val sb = StringBuilder()
        repeat(q) {
            sb.appendLine(treeInfo[readLine().toInt()])
        }
        println(sb)
    }

    fun dfs(root: Int) {
        tree[root].forEach { t ->
            if (visited[t].not()) {
                visited[t] = true
                dfs(t)
                treeInfo[root] += treeInfo[t]
            }
        }
    }

}

fun main() {
    `트리와 쿼리`().solve()
}