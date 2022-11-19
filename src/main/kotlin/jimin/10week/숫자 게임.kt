package jimin.`10week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

/*
<문제>
[숫자 게임](https://www.acmicpc.net/problem/2303)

<구현 방법>
5C3 조합을 구하면서 합의 일의 자리를 최대수로 갱신한다.
이때 이미 9가 나왔다면 그것이 제일 큰 수이므로 바로 빠져나오게 한다.

<트러블 슈팅>
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val numberInfo = mutableListOf<MutableList<Int>>()
    var maxNum = 0
    var maxPerson = 1
    repeat(readLine().toInt()) {
        numberInfo.add(readLine().split(" ").map { it.toInt() }.toMutableList())
        val result = pickMaxNum(numberInfo.last())
        if (result >= maxNum) {
            maxNum = result
            maxPerson = it + 1
        }
    }
    println(maxPerson)
}

fun pickMaxNum(numberInfo: MutableList<Int>): Int {
    var maxNum = 0
    out@ for (i in 0 until numberInfo.size) {
        for (j in i + 1 until numberInfo.size) {
            for (k in j + 1 until numberInfo.size) {
                maxNum = max(maxNum, (numberInfo[i] + numberInfo[j] + numberInfo[k]) % 10)
                if (maxNum == 9) {
                    break@out
                }
            }
        }
    }
    return maxNum
}
