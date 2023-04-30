package heejik.`31week`

import kotlin.math.min
import kotlin.properties.Delegates

class 운동 {

    var b by Delegates.notNull<Int>()
    var v by Delegates.notNull<Int>()
    private lateinit var distances: MutableList<MutableList<Int>>
    fun solve() {
        setting()
        setShortcut()
        getMinCycle().also { println(it) }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            b = this[0]
            v = this[1]
        }
        distances = MutableList(b) { MutableList(b) { Int.MAX_VALUE } }

        repeat(v) {
            val (a, b, distance) = readln().split(' ').map { it.toInt() }
            distances[a - 1][b - 1] = distance
        }
    }

    private fun setShortcut() {
        repeat(b) { middle ->
            repeat(b) { start ->
                repeat(b) { end ->
                    if (distances[start][middle] + distances[middle][end] > 0) {
                        distances[start][end] =
                            min(distances[start][end], distances[start][middle] + distances[middle][end])
                    }
                }
            }
        }
    }

    private fun getMinCycle(): Int {
        var answer = Int.MAX_VALUE
        repeat(b) { start ->
            repeat(b) { end ->
                val cycle = distances[start][end] + distances[end][start]
                if (cycle > 0) answer = min(cycle, answer)
            }
        }
        return if (answer != Int.MAX_VALUE) answer else -1
    }
}

fun main() {
    운동().solve()
}