package jimin.`5week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/*
<문제>
[바닥 장식] (https://www.acmicpc.net/problem/1388)

<구현 방법>
가로 무늬, 세로 무늬의 판자 개수를 각각 판단하는 함수를 만들어 개수를 구했다.

<트러블 슈팅>
 */
val bottomDesign = mutableListOf<MutableList<String>>()
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    repeat(n) {
        bottomDesign.add(readLine().chunked(1).toMutableList())
    }
    println(checkRow(n,m) + checkColumn(n,m))
}

fun checkRow(n: Int, m: Int): Int {
    var num = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (j == m - 1 && bottomDesign[i][j] == "-") {
                num += 1
            } else if (bottomDesign[i][j] == "-" && bottomDesign[i][j + 1] == "|") {
                num += 1
            }
        }
    }
    return num
}

fun checkColumn(n: Int, m: Int): Int {
    var num = 0
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (j == n - 1 && bottomDesign[j][i] == "|") {
                num += 1
            } else if (bottomDesign[j][i] == "|" && bottomDesign[j + 1][i] == "-") {
                num += 1
            }
        }
    }
    return num
}
