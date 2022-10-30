package heejik.`7week`

import java.util.*

class `최소 힙` {

    companion object {

        fun solve() {
            val priorityQueue = PriorityQueue<Int>()

            repeat(readln().toInt()) {
                val x = readln().toInt()
                if (x == 0) {
                    println(priorityQueue.poll().run {
                        this ?: 0
                    })
                } else {
                    priorityQueue.add(x)
                }
            }
        }
    }
}

fun main() {
    `최소 힙`.solve()
}
