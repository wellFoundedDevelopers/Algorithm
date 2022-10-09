package heejik.`4week`

import java.util.Collections.min


var n: Int = 0
val li = arrayListOf<List<Int>>()
val score_li = arrayListOf<Int>()

fun main() {

    n = readln().toInt()

    repeat(n) {
        li.add(readln().split(' ').map { it.toInt() })
    }

    li.forEachIndexed { x, _ ->
        li[x].forEachIndexed { y, i ->
            if (i != 0) {
                solve(x, x, y, listOf(x, y), i)
            }
        }
    }
    println(min(score_li))
}

fun solve(start: Int, from: Int, to: Int, city: List<Int>, score: Int) {
    if (city.size == n) {
        if (li[to][start] != 0) {
            score_li.add(score + li[to][start])
        }
        return
    }

    for (i in 0 until n) {
        if (i !in city && i != from && li[to][i] != 0) {
            solve(start, to, i, city.plus(i), score + li[to][i])
        }
    }
}