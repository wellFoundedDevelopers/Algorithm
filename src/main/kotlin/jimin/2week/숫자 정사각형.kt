package jimin.`2week`

/*
<문제>
https://www.acmicpc.net/problem/1051

<구현 방법>
가로, 세로 중 더 작은 것을 rectMax로 두고, 길이가 2부터 rectMax까지 사각형을 만들며 같은 수가 있는지 찾았다.

<트러블 슈팅>
처음에는 숫자들 중에 4개 이상인 것만 추출해서 그걸 기반으로 찾았다.
그리고 column를 먼저 돌고 거기서 추출한 숫자가 있다면 이동한 영역만큼 row을 이동하는 식으로 진행했었다.
 */

import kotlin.math.max
import kotlin.math.min

fun main() {
    val num = readln().split(" ").map { it.toInt() }
    val rectList = arrayListOf<List<Int>>()
    for (i in 0 until num[0]) {
        rectList.add(readln().chunked(1).map { it.toInt() })
    }
    val rectMax = min(num[0], num[1])
    var maxSize = 1
    for (i in 0 until num[0]) {
        for (j in 0 until num[1]) {
            for (k in 1 until rectMax) {
                if (j + k < num[1] && rectList[i][j] == rectList[i][j + k]) {
                    if (i + k < num[0] && rectList[i][j] == rectList[i + k][j]) {
                        if (rectList[i][j] == rectList[i + k][j + k]) {
                            maxSize = max(maxSize, (k + 1) * (k + 1))
                        }
                    }
                }
            }
        }
    }

    println(maxSize)
}
/*

fun getRectMaxSize(rectList: ArrayList<List<Int>>): Int {
    val rectSet = rectList.flatten().toSet()
    val possibleRectNumber = arrayListOf<Int>()
    rectSet.forEach { set ->
        if (rectList.flatten().count { it == set } >= 4) {
            possibleRectNumber.add(set)
        }
    }
    var max = 1
    for ((i, row) in rectList.withIndex()){
        for ((j,column) in row.withIndex()){
            if (column !in possibleRectNumber) continue
            for (y in j + 1 until row.size){
                if (row[y] == column) {
                    for (x in i + 1 until rectList.size) {
                        if (column == rectList[x][j]) {
                            if (column == rectList[x][y]) {
                                max = max(max, (x - i + 1) * (y - j + 1))
                                //possibleRectNumber.remove(column)
                            }
                        }
                    }
                }
            }
        }
    }
    return max
}
*/
