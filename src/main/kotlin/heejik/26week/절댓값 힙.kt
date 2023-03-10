package heejik.`26week`

import java.util.PriorityQueue
import kotlin.math.absoluteValue

class `절댓값 힙` {

    val pq = PriorityQueue(compareBy<Int> { it.absoluteValue }.thenComparator { a, b -> a - b })
    fun solve() {
        val n = readln().toInt()
        repeat(n) {
            val x = readln().toInt()
            if (x == 0) {
                pq.poll().run {
                    println(this ?: 0)
                }
            } else {
                pq.add(x)
            }
        }
    }
}

fun main() {
    `절댓값 힙`().solve()
}