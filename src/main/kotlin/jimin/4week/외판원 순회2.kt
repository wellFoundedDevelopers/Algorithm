package jimin.`4week`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val priceList = mutableListOf<MutableList<Int>>()
    val checkList = arrayListOf<Boolean>()
    repeat(readLine().toInt()){
        priceList.add(readLine().split(" ").map { it.toInt() }.toMutableList())
        checkList.add(false)
    }
    var minPrice = Int.MAX_VALUE

}