package jimin.`8week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[창고 다각형] https://www.acmicpc.net/problem/2304

<구현 방법>
가장 큰 기둥을 기준으로 첫번째 기둥부터 가장 큰 기둥, 마지막 기둥에서 가장 큰 기둥으로
for문을 돌면서 첫번째나 마지막 기둥보다 큰 기둥을 구하고 영역을 더해주었다.

<트러블 슈팅>
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val pillarList = mutableListOf<Pair<Int, Int>>()
    repeat(readLine().toInt()) {
        val (l, h) = readLine().split(" ").map { it.toInt() }
        pillarList.add(Pair(l, h))
    }
    pillarList.sortWith(compareBy { it.first })

    val realMaxIndex = pillarList.map { it.second }.indexOfLast { it == pillarList.maxOf { it.second } }
    var max = pillarList.first()
    var result = 0
    pillarList.forEachIndexed { index, pair ->
        if (realMaxIndex == index) {
            result += pair.second
        }
        if (pair.second >= max.second) {
            result += max.second * (pair.first - max.first)
            max = pair
        }
    }
    //println(result)
    max = pillarList.last()
    for (i in pillarList.size -1 downTo realMaxIndex){
        if (pillarList[i].second >= max.second) {
            result += max.second * (max.first - pillarList[i].first)
            max = pillarList[i]
        }
    }
    println(result)
}
