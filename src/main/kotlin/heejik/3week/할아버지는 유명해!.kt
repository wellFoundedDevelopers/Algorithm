package heejik.`3week`

import kotlin.system.exitProcess

fun main() {

    while (true) {
        val (n, m) = readln().trim().split(' ').map { it.toInt() }
            .also { if (it.contains(0)) exitProcess(0) }

        val li = mutableMapOf<Int,Int>()
        repeat(n) {
             readln().trim().split(' ').map { it.toInt() }.forEach {
                 li[it] = li[it]?.run { this+1 } ?: 0
             }
        }

        val secondScore = li.values.sortedDescending()[1]
        li.filterKeys { li[it] == secondScore }.toSortedMap().forEach {
            print("${it.key} ")
        }
        println()
    }
}