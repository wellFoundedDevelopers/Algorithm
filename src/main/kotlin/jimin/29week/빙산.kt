package jimin.`29week`

/*
<문제>
[빙산](https://www.acmicpc.net/problem/2573)

<구현 방법>
1년이 지나 빙산이 녹는 것을 구현하는 age() 함수와 녹은 후 빙산의 개수를 구하는 getMountainNum() 함수를 구현하였다.
이때 빙산이 녹는 것은 한번에 녹기 때문에 바다 개수를 melting에 담아놓고 한번 더 for문을 써서 빼주었다.

<트러블 슈팅>
문제를 잘 읽자22
*/


import java.lang.Integer.*

class 빙산 {
    val mountains = mutableListOf<MutableList<Int>>()
    val dx = mutableListOf(0, 0, -1, 1)
    val dy = mutableListOf(1, -1, 0, 0)
    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        repeat(n) {
            mountains.add(readln().split(" ").map { it.toInt() } as MutableList)
        }
        var result = 1

        while (true) {
            age(n, m)
            val num = getMountainNum(n, m)
            if (num >= 2) {
                break
            } else if (num == 0) {
                result = 0
                break
            }
            result += 1
        }
        println(result)

    }

    fun age(n: Int, m: Int) {
        val melting = MutableList(n) { MutableList(m) { 0 } }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (mountains[i][j] != OCEAN) {
                    for (k in 0 until 4) {
                        if (mountains[i + dx[k]][j + dy[k]] == OCEAN) {
                            melting[i][j] += 1
                        }
                    }
                }
            }
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                mountains[i][j] = max(0, mountains[i][j] - melting[i][j])
            }
        }
    }

    fun getMountainNum(n: Int, m: Int): Int {
        val visited = MutableList(n) { MutableList(m) { false } }
        var num = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (mountains[i][j] != OCEAN && visited[i][j].not()) {
                    bfs(n, m, visited, i, j)
                    num++
                }
            }
        }
        return num
    }

    fun bfs(n: Int, m: Int, visited: MutableList<MutableList<Boolean>>, x:Int, y:Int) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(x, y))

        while(queue.isNotEmpty()) {
            val now = queue.removeLast()
            for (i in 0 until 4) {
                if (now.first + dx[i] in 0 until n && now.second + dy[i] in 0 until m &&
                        visited[now.first + dx[i]][now.second + dy[i]].not() &&
                        mountains[now.first + dx[i]][now.second + dy[i]] != OCEAN) {
                    queue.addFirst(Pair(now.first + dx[i], now.second + dy[i]))
                    visited[now.first + dx[i]][now.second + dy[i]] = true
                }
            }
        }

    }

    companion object {
        const val OCEAN = 0
    }
}

fun main() {
    `빙산`().solve()
}