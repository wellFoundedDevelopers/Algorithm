package jimin.`2week`

import kotlin.math.max

/*
<문제>
https://www.acmicpc.net/problem/1337

<구현 방법>
count 고차함수를 사용하여 자기 자신 이상, 자기 자신 + 4 이하인 것을 찾아 최댓값을 구해 5에서 빼주었다.

<트러블 슈팅>
 */

fun main() {
    var numberList = arrayListOf<Int>()
    repeat(readln().toInt()){
        numberList.add(readln().toInt())
    }
    var max = 1
    numberList.forEachIndexed { index, num ->
        val continuous = numberList.count { it <= num + 4 && it >= num }
        max = max(max, continuous)
    }
    println(5 - max)
}