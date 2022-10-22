package jimin.`6week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*

/*
<문제>
[아기 상어 2] (https://www.acmicpc.net/problem/17086)

<구현 방법>
상어가 존재하는 1인 곳만 bfs를 돌아 거리를 구했고, 두번째 상어부터는 이전 상어와의 거리보다 작을 때 업데이트 해주었다.

<트러블 슈팅>
일단 bfs를 까먹어서 다시 봤다.
처음에는 nxm의 모든 구역을 다 돌아야하는 줄 알았는데, 1인 곳만 돌면 됐었다..
상어가 있는 1을 살리기 위해 거리를 음수로 구했다가 계속 틀려서 그냥 양수로 했더니 성공했다.
 */

val direction = listOf(
    Pair(0, 1),
    Pair(0, -1),
    Pair(1, 0),
    Pair(-1, 0),
    Pair(1, 1),
    Pair(-1, 1),
    Pair(1, -1),
    Pair(-1, -1)
)


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val sharkInfoList = mutableListOf<MutableList<Int>>()

    repeat(n) {
        sharkInfoList.add(readLine().split(" ").map { it.toInt() }.toMutableList())

    }

    repeat(n) { x ->
        repeat(m) { y ->
            if (sharkInfoList[x][y] == 1) {
                val visited = mutableListOf<MutableList<Boolean>>()
                repeat(n) {
                    visited.add(MutableList(m) { i -> false })
                }
                bfs(sharkInfoList, x, y, visited)
            }
        }
    }

    println(sharkInfoList.flatten().maxOf { it } - 1)

}

fun bfs(sharkInfoList: MutableList<MutableList<Int>>, x: Int, y: Int, visited: MutableList<MutableList<Boolean>>) {
    val queue = arrayListOf<Pair<Int, Int>>()
    queue.add(Pair(x, y))
    visited[x][y] = true

    while (queue.isNotEmpty()) {
        val v = queue.removeFirst()
        direction.forEach {
            if (v.first + it.first in 0 until sharkInfoList.size &&
                v.second + it.second in 0 until sharkInfoList.first().size
            ) {
                if (visited[v.first + it.first][v.second + it.second].not()) {
                    queue.add(Pair(v.first + it.first, v.second + it.second))
                    visited[v.first + it.first][v.second + it.second] = true
                    //println("값: ${sharkInfoList[x][y]}, x:$x, y:$y")
                    if (sharkInfoList[v.first + it.first][v.second + it.second] == 0 ||
                        sharkInfoList[v.first][v.second] + 1 < sharkInfoList[v.first + it.first][v.second + it.second]) {
                        sharkInfoList[v.first + it.first][v.second + it.second] = sharkInfoList[v.first][v.second] + 1
                        //println(sharkInfoList)
                    }
                }
            }
        }
    }
}

/*
4 4
0 0 0 0
0 0 0 0
0 0 0 0
1 0 0 0

5 4
0 0 1 0
0 0 0 0
1 0 0 0
0 0 0 0
0 0 0 1

 */