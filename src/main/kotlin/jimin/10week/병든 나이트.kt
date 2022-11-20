package jimin.`10week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.min

/*
<문제>
[병든 나이트](https://www.acmicpc.net/problem/1783)

<구현 방법>
높이가 3이상이면 4가지 조합을 다 사용할 수 있다.
높이가 3 미만이라면 [1칸 아래로, 2칸 오른쪽] 과 [2칸 아래로, 1칸 오른쪽]만 사용할 수 있다.
이를 고려해 해결하였다.

<트러블 슈팅>

 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (height, width) = readLine().split(" ").map { it.toInt() }
    var num = 1
    if (height >= 3) {
        if (width >= 7) {
            num = width - 2
        } else {
            num = min(4,width)
        }
    } else if (height == 2) {
        if (width in 3..4) {
            num = 2
        } else if (width in 5 .. 6) {
            num = 3
        } else if (width >= 7) {
            num = 4
        }
    }
    println(num)

}