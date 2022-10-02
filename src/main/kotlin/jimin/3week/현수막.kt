package jimin.`3week`

/*
<문제>
https://www.acmicpc.net/problem/17413

<구현 방법>
dfs를 이용하여 1인 경우를 탐색하도록 함.
이때 방문했으면 0으로 바꿔놓음.

<트러블 슈팅>
dfs 알고리즘 찾아봄
 */

import java.io.BufferedReader
import java.io.InputStreamReader

val banner = mutableListOf<MutableList<Int>>()
var n = 0
var m = 0
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (num1, num2) = readLine().split(" ").map { it.toInt() }
    n = num1
    m = num2
    for (i in 0 until n) {
        banner.add(readLine().split(" ").map { it.toInt() }.toMutableList())
    }
    var count = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (banner[i][j] == 1) {
                dfs(i, j)
                count += 1
            }
        }
    }
    println(count)
}

fun dfs(i: Int, j: Int) {
    val direction = arrayListOf(
        Pair(-1, -1),
        Pair(-1, 0),
        Pair(-1, 1),
        Pair(0, -1),
        Pair(0, 1),
        Pair(1, -1),
        Pair(1, 0),
        Pair(1, 1),
    )
    banner[i][j] = 0
    for ((x, y) in direction) {
        if (i + x < n && i + x >= 0 && j + y < m && j + y >= 0) {
            if (banner[i + x][j + y] == 1) {
                dfs(i + x, j + y)
            }
        }

    }
}

