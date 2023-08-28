package jimin.`42week`

/*
<문제>
[바이러스](https://www.acmicpc.net/problem/2606)

<구현 방법>
bfs를 이용했다

<트러블 슈팅>
 */

import java.util.*

class 바이러스 {
    fun solve() {
        val n = readln().toInt()
        val m = readln().toInt()
        val computers = MutableList<MutableList<Int>>(n + 1) { mutableListOf() }
        repeat(m) {
            val (a, b) = readln().split(" ").map{ it.toInt() }
            computers[a].add(b)
            computers[b].add(a)
        }

        bfs(computers, n)
    }

    fun bfs(computers: MutableList<MutableList<Int>>, n:Int){
        val deque = ArrayDeque<Int>()
        val visited = MutableList(n + 1){false}
        var total = -1
        deque.add(1)
        visited[1] = true

        while (deque.isNotEmpty()) {
            val now = deque.removeFirst()
            total += 1
            val connections = computers[now]
            connections.forEach{ c ->
                if (visited[c].not()){
                    deque.addLast(c)
                    visited[c] = true
                }
            }
        }

        println(total)
    }
}

fun main() {
    바이러스().solve()
}