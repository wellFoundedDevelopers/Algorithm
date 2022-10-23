package heejik.`5week`

import kotlin.system.exitProcess

val bingo = arrayListOf<MutableList<Int>>()

fun main() {
    var cnt = 0
    val chairMan = arrayListOf<MutableList<Int>>()

    repeat(5) {
        bingo.add(readln().split(' ').map { it.toInt() }.toMutableList())
    }
    repeat(5) {
        chairMan.add(readln().split(' ').map { it.toInt() }.toMutableList())
    }

    chairMan.forEachIndexed { _, ints ->
        ints.forEachIndexed { _, num ->
            bingo.forEachIndexed { _, row ->
                if (num in row) {
                    cnt += 1
                    row[row.indexOf(num)] = -1
                    if (isBingo() >= 3) {
                        println(cnt)
                        exitProcess(0)
                    }
                }
            }
        }
    }
}

fun isBingo(): Int {
    var cnt = 0
    var tmp = 0
    repeat(5) { i ->
        if (bingo[i].filter { it == -1 }.size == 5) cnt += 1
    }

    repeat(5) { j ->
        tmp = 0
        repeat(5) { i ->
            if (bingo[i][j] == -1) {
                tmp += 1
            }
            if (tmp == 5) cnt += 1
        }
    }

    tmp = 0
    repeat(5) { i ->
        if (bingo[i][i] == -1) tmp += 1
    }
    if (tmp == 5) cnt += 1

    tmp = 0
    repeat(5) { i ->
        if (bingo[i][4 - i] == -1) tmp += 1
    }
    if (tmp == 5) cnt += 1

    return cnt
}

