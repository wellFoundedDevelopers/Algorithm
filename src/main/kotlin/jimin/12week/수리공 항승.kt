package jimin.`12week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[수리공 항승](https://www.acmicpc.net/problem/1449)

<구현 방법>
테이프를 구멍난 첫번째로 두고 테이프 + 길이 보다 다음 구멍난 곳이 크면
테이프 위치를 업데이트 해준다.

<트러블 슈팅>

 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (_, length) = readLine().split(" ").map { it.toInt() }
    val waters = readLine().split(" ").map { it.toInt() }.toMutableList()

    waters.sort()
    var tape = waters[0]
    var num = 1
    waters.forEach {water ->
        if (water !in tape until tape + length) {
            tape = water
            num += 1
        }
    }
    println(num)
}
