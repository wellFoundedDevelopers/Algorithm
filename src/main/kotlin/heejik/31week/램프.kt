package heejik.`31week`

import kotlin.math.max
import kotlin.properties.Delegates

class 램프 {

    private var n by Delegates.notNull<Int>()
    private var m by Delegates.notNull<Int>()
    private var k by Delegates.notNull<Int>()
    private val lamp = mutableListOf<BooleanArray>()

    fun solve() {
        setting()
        getAnswer().also {
            println(it)
        }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        repeat(n) {
            lamp.add(readln().map { it.digitToInt() % 2 != 0 }.toBooleanArray())
        }
        k = readln().toInt()
    }

    private fun getAnswer(): Int {
        var answer = 0
        repeat(n) { r ->
            val cnt = lamp[r].count { it.not() }
            if (cnt <= k && (k % 2 == cnt % 2)) {
                answer = max(answer, lamp.count { it.contentEquals(lamp[r]) })
            }
        }

        return answer
    }
}

fun main() {
    램프().solve()
}