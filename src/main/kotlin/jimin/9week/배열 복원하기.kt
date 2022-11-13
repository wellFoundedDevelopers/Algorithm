package jimin.`9week`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (h, w, x, y) = readLine().split(" ").map { it.toInt() }
    val bList = mutableListOf<MutableList<Int>>()
    val aList = MutableList(h) { MutableList(w) { 0 } }
    repeat(h + x) {
        bList.add(readLine().split(" ").map { it.toInt() }.toMutableList())
    }

    for (i in 0 until h) {
        for (j in 0 until w) {
            if (i >= x && j >= y) {
                aList[i][j] = bList[i][j] - aList[i - x][j - y]
            } else {
                aList[i][j] = bList[i][j]
            }
        }
    }
    aList.forEach { println(it.joinToString(" ")) }
}
