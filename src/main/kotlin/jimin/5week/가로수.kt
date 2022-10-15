package jimin.`5week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/*
<문제>
[가로수] https://www.acmicpc.net/problem/2485

<구현 방법>
가로수들의 간격을 구하고, 그 간격의 최솟값부터 1까지 downTo로 for문을 돌았다.
돌면서 각 간격들을 나눴을 때 나머지가 모두 0인 것을 찾고, 최소 가로수 수를 구했다.

<트러블 슈팅>
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val treeList = arrayListOf<Int>()
    repeat(readLine().toInt()) {
        treeList.add(readLine().toInt())
    }

    treeList.sort()

    val treeDistanceList = arrayListOf<Int>()
    treeList.forEachIndexed { index, _ ->
        if (index != treeList.size - 1) {
            treeDistanceList.add(treeList[index + 1] - treeList[index])
        }
    }

    val minDistance = treeDistanceList.minOf { it }
    var sum = 0
    for (i in minDistance downTo 1){
        if (treeDistanceList.all { it % i == 0}) {
            treeDistanceList.forEach {
                sum += it / i - 1
            }
            println(sum)
            break
        }
    }
}
