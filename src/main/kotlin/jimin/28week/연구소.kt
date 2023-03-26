package jimin.`28week`

/*
<문제>
[연구소](https://www.acmicpc.net/problem/14502)

<구현 방법>
완탐!
우선 3가지 벽을 고르는 combination을 만들고,
그 후 dfs로 바이러스 감염되는 곳을 다 방문하고, 살아남은 곳만 count했다.

<트러블 슈팅>
*/

import java.lang.Integer.*
class 연구소 {
    val labs = mutableListOf<MutableList<Int>>()
    val viruses = mutableListOf<Pair<Int, Int>>()
    val dx = mutableListOf(0, 0, -1, 1)
    val dy = mutableListOf(1, -1, 0, 0)
    var result = 0

    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        repeat(n) {
            labs.add(readln().split(" ").map { it.toInt() } as MutableList)
        }
        repeat(n) { i ->
            repeat(m) { j ->
                if (labs[i][j] == VIRUS) {
                    viruses.add(Pair(i, j))
                }
            }
        }
        getCombination(listOf())
        println(result)

    }

    fun getCombination(walls: List<Pair<Int, Int>>) {
        if (walls.size == 3) {
            bfs(walls)
            return
        }

        for (i in 0 until labs.size) {
            for (j in 0 until labs.first().size) {
                if (labs[i][j] == EMPTY && Pair(i, j) !in walls) {
                    getCombination(walls.plus(Pair(i, j)))
                }
            }
        }
    }

    fun bfs(walls: List<Pair<Int, Int>>) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val rooms = labs.deepCopy()
        walls.forEach {
            rooms[it.first][it.second] = WALL
        }
        viruses.forEach{
            queue.add(it)
        }

        while (queue.isNotEmpty()) {
            val now = queue.removeLast()
            for (i in 0 until 4) {
                if (now.first + dx[i] in 0 until rooms.size && now.second + dy[i] in 0 until rooms.first().size) {
                    if (rooms[now.first + dx[i]][now.second + dy[i]] == EMPTY) {
                        queue.add(Pair(now.first + dx[i], now.second + dy[i]))
                        rooms[now.first + dx[i]][now.second + dy[i]] = VISITED
                    }
                }
            }
        }
        result = max(rooms.sumOf{ it.count{ it == 0 } }, result)

    }

    fun MutableList<MutableList<Int>>.deepCopy(): MutableList<MutableList<Int>> {
        val new = mutableListOf<MutableList<Int>>()
        this.forEach {
            new.add(it.toMutableList())
        }
        return new
    }

    companion object{
        const val EMPTY = 0
        const val WALL = 1
        const val VIRUS = 2
        const val VISITED = -1
    }
}

fun main() {
    `연구소`().solve()
}