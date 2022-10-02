package jimin.`3week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
https://www.acmicpc.net/problem/13305

<구현 방법>
nowPrice에 처음 가격으로 초기화를 한 뒤에, for문을 돌면서 nowPrice보다 가격이 크면 그대로 유지하고,
작으면 nowPrice를 업데이트하는 식으로 진행하였다.


<트러블 슈팅>
100점이 안나오고 58점이 나온다 ㅠ
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    readLine().toInt()
    val length = readLine().split(" ").map { it.toInt() }.toMutableList()
    length.add(0, 0)
    val price = readLine().split(" ").map { it.toInt() }

    var totalCharge = 0
    var nowPrice = price[0]
    for (i in 1 until length.size){
        totalCharge += nowPrice * length[i]
        if (nowPrice > price[i]) {
            nowPrice = price[i]
        }
    }

    println(totalCharge)
}