package jimin.`8week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[문자열 집합] https://www.acmicpc.net/problem/14425

<구현 방법>
contains()를 사용해 집합에 포함되었는지 판단함.

<트러블 슈팅>
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val strList = mutableListOf<String>()
    repeat(n) {
        strList.add(readLine())
    }
    var result = 0
    repeat(m) {
        val word = readLine()
        if (strList.contains(word)) {
            result += 1
        }
    }
    println(result)

}