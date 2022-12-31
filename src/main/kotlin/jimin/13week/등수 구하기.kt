package jimin.`13week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[등수 구하기](https://www.acmicpc.net/problem/1205)

<구현 방법>
n이 0 이면 무조건 1 출력,
n과 p가 같으면 점수 꼴지가 s와 같거나 작다면 -1 출력,
아니면 scoreList에 add해서 내림차순정렬해서 index구하기

<트러블 슈팅>
*/

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, s, p) = readLine().split(" ").map { it.toInt() }
    if (n == 0) {
        println(1)
        return@with
    }
    val scoreList = readLine().split(" ").map { it.toInt() }.toMutableList()
    if (n == p) {
        if (scoreList.last() >= s) {
            println(-1)
            return@with
        }
    }
    scoreList.add(s)
    scoreList.sortDescending()
    println(scoreList.indexOf(s) + 1)
}
