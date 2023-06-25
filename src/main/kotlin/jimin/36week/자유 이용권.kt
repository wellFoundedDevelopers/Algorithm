package jimin.`36week`

// https://lighter.tistory.com/161

import java.util.*

class `자유 이용권` {


    fun solve() {
        val n = readln()
//        val pq = PriorityQueue(Comparator<Pair<Int, Long>> { a, b -> if (a.second == b.second) a.second - b.second else b.second - a.second })
//        readln().split(" ").map { it.toLong() }.forEachIndexed { idx, it ->
//            pq.add(Pair(idx, it))
//        }

//        val rides = mutableListOf<Int>()
//
//        var num = 0
//        while (pq.isNotEmpty()) {
//            val maxi = pq.poll()
//
//            if (rides.isEmpty() || maxi.first != rides.last()) {
//                rides.add(maxi.first)
//                if (maxi.second - 1 > 0) {
//                    pq.add(Pair(maxi.first, maxi.second - 1))
//                }
//            } else {
//                if (pq.isEmpty()) {
//                    break
//                } else {
//                    val secondMaxi = pq.poll()
//                    rides.add(secondMaxi.first)
//                    pq.add(maxi)
//                    if (secondMaxi.second - 1 > 0) {
//                        pq.add(Pair(secondMaxi.first, secondMaxi.second - 1))
//                    }
//                }
//            }
//
//            num += 1
//
//            if (num == 10) {
//                break
//            }
//        }
//
//        println(rides.size)

        val rides = readln().split(" ").map { it.toLong() } as MutableList

        val maxi = rides.maxOf { it }
        val otherSum = rides.sumOf{it} - maxi

        if (maxi <= otherSum + 1) {
            println(maxi + otherSum)
        } else {
            println(otherSum * 2 + 1)
        }
    }

}

fun main() {
    `자유 이용권`().solve()
}