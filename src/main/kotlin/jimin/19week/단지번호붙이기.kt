package jimin.`19week`

/*
<문제>
[단지번호붙이기](https://www.acmicpc.net/problem/2667)

<구현 방법>
nxn을 돌면서 1인 곳을 bfs로 돌면서 단지 수를 세었다.

<트러블 슈팅>
*/

val visited = mutableListOf<MutableList<Boolean>>()
val houses = mutableListOf<List<Int>>()
val dx = mutableListOf(0, 0, 1, -1)
val dy = mutableListOf(1, -1, 0, 0)
var n = 0
fun main() {
    n = readln().toInt()
    repeat(n) {
        houses.add(readln().chunked(1).map { it.toInt() })
        visited.add(MutableList(n) { false })
    }
    val result = mutableListOf<Int>()
    repeat(n) { i ->
        repeat(n) { j ->
            if (visited[i][j].not() && houses[i][j] == 1) {
                result.add(bfs(i,j))
            }
        }
    }
    result.sort()
    println(result.size)
    result.forEach {
        println(it)
    }
}

fun bfs(x: Int, y: Int): Int {
    val queue = mutableListOf(Pair(x, y))
    var sum = 1
    visited[x][y] = true
    while (queue.isNotEmpty()) {
        val house = queue.removeAt(0)
        for (i in 0..3) {
            if (house.first + dx[i] in 0 until n && house.second + dy[i] in 0 until n &&
                visited[house.first + dx[i]][house.second + dy[i]].not() && houses[house.first + dx[i]][house.second + dy[i]] == 1
            ) {
                queue.add(Pair(house.first + dx[i], house.second + dy[i]))
                visited[house.first + dx[i]][house.second + dy[i]] = true
                sum += 1
            }
        }
    }
    return sum
}