package jimin.`10week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

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
