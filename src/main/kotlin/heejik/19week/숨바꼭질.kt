package heejik.`19week`

import kotlin.properties.Delegates

class 숨바꼭질 {

    enum class Moving {
        BACK, FRONT, TELEPORTATION
    }

    var n by Delegates.notNull<Int>()
    var k by Delegates.notNull<Int>()
    val queue = ArrayDeque<Int>()
    private val timesByDistance = MutableList(100001) { Int.MAX_VALUE }
    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = first()
            k = last()
        }

        timesByDistance[n] = 0
        queue.addFirst(n)
        bfs()
        println(timesByDistance[k])
    }

    private fun bfs() {
        while (queue.isNotEmpty()) {
            val distance = queue.removeFirst()
            move(distance, Moving.BACK)
            if (distance <= k) {
                move(distance, Moving.FRONT)
                move(distance, Moving.TELEPORTATION)
            }
        }
    }

    private fun move(distance: Int, moving: Moving) {
        val preTime = timesByDistance[distance]
        val nextDistance = when (moving) {
            Moving.FRONT -> {
                distance + 1
            }

            Moving.BACK -> {
                distance - 1
            }

            Moving.TELEPORTATION -> {
                distance * 2
            }
        }
        if (nextDistance in 0 until timesByDistance.size && timesByDistance[nextDistance] == Int.MAX_VALUE) {
            queue.add(nextDistance)
            timesByDistance[nextDistance] = preTime + 1
        }
    }
}

fun main() {
    숨바꼭질().solve()
}