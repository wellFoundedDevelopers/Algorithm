package jimin.`5week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[랜선 자르기] (https://www.acmicpc.net/problem/1654)

<구현 방법>
최솟값을 1로, 최댓값을 랜선의 최대로 하여 이분 탐색을 진행하였다.

<트러블 슈팅>
이분 탐색을 진행할 때, min은 length + 1 로 max는 length - 1로 범위를 줄여나가야한다.
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (k, n) = readLine().split(" ").map { it.toInt() }
    val lanCableList = arrayListOf<Long>()
    repeat(k){
        lanCableList.add(readLine().toLong())
    }

    var min = 1L
    var max = lanCableList.sorted().last()
    while (true) {
        if (min > max) {
            println(max)
            break
        }
        val length = (min + max) / 2
        var sum = 0L
        lanCableList.forEach {
            sum += it / length
        }
        if (sum >= n) {
            min = length + 1
        } else {
            max = length - 1
        }
    }
}
