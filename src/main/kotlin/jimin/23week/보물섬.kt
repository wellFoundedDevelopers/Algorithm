package jimin.`23week`

/*
<문제>
[보물섬](https://www.acmicpc.net/problem/2589)

<구현 방법>
bfs를 응용하였다.
현재 queue에 있는 것을 모두 꺼내서 갈 수 있는 영역을 subQueue에 담고,
queue를 clear 시킨후 subQueue의 내용을 queue에 담아 dfs를 구현했다.

<트러블 슈팅>
 */

import java.lang.Integer.*

class 이지민_보물섬 {
    lateinit var map: MutableList<MutableList<String>>
    val dx = mutableListOf(0, 0, 1, -1)
    val dy = mutableListOf(1, -1, 0, 0)
    var result = 0

    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        map = MutableList(n + 2) { MutableList(m + 2) { "W" } }
        for (i in 0 until n) {
            readln().chunked(1).forEachIndexed { j, it ->
                map[i + 1][j + 1] = it
            }
        }

        for (i in 1..n) {
            for (j in 1..m) {
                if (map[i][j] == "L") {
                    result = max(result, bfs(map.deepCopy(), i, j))

                }
            }
        }

        println(result)

    }

    fun bfs(map: MutableList<MutableList<String>>, x: Int, y: Int): Int {
        var queue = mutableListOf(Pair(x, y))
        var sum = - 1
        map[x][y] = "W"

        while(queue.isNotEmpty()) {
            val subQueue = mutableListOf<Pair<Int, Int>>()
            queue.forEach{ num ->
                for (i in 0 until 4) {
                    if(map[num.first + dx[i]][num.second + dy[i]] == "L") {
                        subQueue.add(Pair(num.first + dx[i], num.second + dy[i]))
                        map[num.first + dx[i]][num.second + dy[i]] = "W"
                    }
                }
            }
            queue.clear()
            queue = subQueue.toMutableList()
            subQueue.clear()

            sum += 1
        }
        return sum
    }

    private fun MutableList<MutableList<String>>.deepCopy(): MutableList<MutableList<String>> {
        val new = mutableListOf<MutableList<String>>()
        this.forEach {
            new.add(it.toMutableList())
        }
        return new
    }
}

fun main() {
    이지민_보물섬().solve()
}