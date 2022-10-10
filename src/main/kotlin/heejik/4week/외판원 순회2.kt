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

    // 0 에서만 시작지점
    for (i in 1 until n){
        if (li[0][i] != 0) {
            solve(0, 0, i, listOf(0, i), li[0][i])
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