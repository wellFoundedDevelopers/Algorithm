package jimin.`4week`

/*
<문제>
https://www.acmicpc.net/problem/1926

<구현 방법>
최대 그림 넓이(maxNum)와, 그냥 그림 넓이(area)를 전역변수로 두어
dfs로 그림을 pictureList를 돌아다닐 때 그냥 그림 넓이를 갱신하고, 최대를 갱신한다.
그냥 그림 넓이는 1일때 0으로 초기화한다.

<트러블 슈팅>
area와 maxNum은 dfs의 처음에 갱신해주어야 한다. (for문의 if문에서 하면 안됨)
 */

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max

var maxNum = 0
var area = 0
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val pictureList = mutableListOf<MutableList<Int>>()
    repeat(n) {
        pictureList.add(readLine().split(" ").map { it.toInt() }.toMutableList())
    }

    var pictureNumber = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (pictureList[i][j] == 1){
                dfs(pictureList, i, j)
                pictureNumber += 1
                //여기에 maxNum = max(maxNum, area) 갱신
                area = 0
            }
        }
    }
    println(pictureNumber)
    println(maxNum)
}

fun dfs(pictureList: MutableList<MutableList<Int>>, i: Int, j: Int) {
    pictureList[i][j] = 0
    area += 1
    maxNum = max(maxNum, area)
    val direction = arrayListOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))
    direction.forEach {
        if (i + it.first in 0 until pictureList.size &&
            j + it.second in 0 until pictureList[0].size &&
            pictureList[i + it.first][j + it.second] == 1) {
            dfs(pictureList, i + it.first, j + it.second)
        }
    }
}

