package jimin.`35week`

/*
<문제>
[인구이동](https://www.acmicpc.net/problem/16234)

<구현 방법>
nxn을 돌면서 해당 위치의 땅으로 부터 bfs를 돌았다.
이때 idx를 두어서 지금이 같은 날짜이라는 것을 일깨워주었다.

<트러블 슈팅>
*/


import java.lang.Math.abs

class `인구 이동` {
    private val countries = mutableListOf<MutableList<Pair<Int, Int>>>()
    private val dx = mutableListOf(0, 0, 1, -1)
    private val dy = mutableListOf(-1, 1, 0, 0)
    fun solve() {
        val (n, l, r) = readln().split(" ").map { it.toInt() }
        repeat(n) {
            countries.add(readln().split(" ").map { Pair(it.toInt(), 0) } as MutableList)
        }

        var idx = 0
        var over = true
        while (true) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (countries[i][j].second <= idx) {
                        val union = bfs(i, j, n, l, r, idx)
                        if (union.size == 1) {
                            continue
                        }
                        val sum = union.sumOf { countries[it.first][it.second].first } / union.size
                        union.forEach {
                            countries[it.first][it.second] = Pair(sum, countries[it.first][it.second].second)
                        }
                        over = false

                    }
                }
            }

            if (over) {
                break
            }
            idx += 1
            over = true
        }

        println(idx)

    }

    fun bfs(x: Int, y: Int, n: Int, l: Int, r: Int, idx: Int): MutableList<Pair<Int, Int>> {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(x, y))
        countries[x][y] = Pair(countries[x][y].first, countries[x][y].second + 1)

        val union = mutableListOf(Pair(x, y))

        while (queue.isNotEmpty()) {
            val (nx, ny) = queue.removeFirst()
            for (i in 0 until 4) {
                if (nx + dx[i] in 0 until n && ny + dy[i] in 0 until n &&
                        countries[nx + dx[i]][ny + dy[i]].second <= idx &&
                        abs(countries[nx + dx[i]][ny + dy[i]].first - countries[nx][ny].first) in l until r + 1) {
                    union.add(Pair(nx + dx[i], ny + dy[i]))
                    queue.addLast(Pair(nx + dx[i], ny + dy[i]))
                    countries[nx + dx[i]][ny + dy[i]] = Pair(countries[nx + dx[i]][ny + dy[i]].first, idx + 1)
                }
            }
        }

        return union
    }
}

fun main() {
    `인구 이동`().solve()
}
