package jimin.`5week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/*
<문제>
빙고 (https://www.acmicpc.net/problem/2578)

<구현 방법>
row 탐색, column 탐색, diagonal 탐색하는 함수를 만들어 빙고 개수를 구했다.

<트러블 슈팅>
 */
val userResult = mutableListOf<MutableList<Int>>()
val hostResult = mutableListOf<MutableList<Int>>()
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {

    repeat(5) {
        userResult.add(readLine().split(" ").map { it.toInt() }.toMutableList())
    }
    repeat(5) {
        hostResult.add(readLine().split(" ").map { it.toInt() }.toMutableList())
    }

    val nowHostResult = mutableListOf<Int>()

    hostResult.flatten().forEachIndexed { idx, it ->
        nowHostResult.add(it)
        var bingo = 0
        bingo += checkRow(nowHostResult)
        bingo += checkColumn(nowHostResult)
        bingo += checkDiagonal(nowHostResult)
        if (bingo >= 3) {
            println(idx + 1)
            return@with
        }
    }

}

fun checkRow(nowHostResult: MutableList<Int>): Int {
    var bingo = 0
    for (i in 0 until 5){
        if (userResult[i].all { it in nowHostResult }){
            bingo += 1
        }
    }
    return bingo
}

fun checkColumn(nowHostResult: MutableList<Int>): Int {
    var bingo = 0
    for (i in 0 until 5){
        var check = true
        for (j in 0 until 5){
            if (userResult[j][i] !in nowHostResult) check = false
        }
        if (check) {
            bingo += 1
        }

    }
    return bingo
}

fun checkDiagonal(nowHostResult: MutableList<Int>): Int {
    var bingo = 0
    var check1 = true
    var check2 = true
    for (i in 0 until 5){
        if (userResult[i][i] !in nowHostResult) check1 = false
        if (userResult[i][4 - i] !in nowHostResult) check2 = false
    }
    if (check1) {
        bingo += 1
    }
    if (check2) {
        bingo += 1
    }

    return bingo
}