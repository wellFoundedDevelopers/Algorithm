package heejik

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    var answer = 0
    val (r, c) = readln().trim().split(' ').map { it.toInt() }
    val li = arrayListOf<List<Int>>()

    repeat(r) {
        val tmp = readln().trim().split(' ').map { it.toInt() }
        li.add(tmp)
    }

    val t = readln().trim().toInt()

    for (i in 0 until r - 2) {
        for (j in 0 until c - 2) {
            val center = li.filterIndexed { index, s -> index in i until i + 3 }.map {
                it.filterIndexed { index, i -> index in j until j + 3 }
            }.flatten().sorted()[4]
            if (center >= t) answer += 1
        }
    }
    println(answer)
}