package jimin.`4week`

/*
<문제>
https://www.acmicpc.net/problem/1026

<구현 방법>
A는 내림차순, B는 오름차순으로 곱해 더했다.

<트러블 슈팅>
 */

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    readLine()
    val A = readLine().split(" ").map { it.toInt() }.sorted().reversed()
    val B = readLine().split(" ").map { it.toInt() }.sorted()
    var sum = 0
    A.forEachIndexed { index, s ->
        sum += A[index] * B[index]
    }
    println(sum)
}