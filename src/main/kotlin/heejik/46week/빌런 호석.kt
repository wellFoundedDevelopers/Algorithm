package heejik.`46week`

import kotlin.properties.Delegates

class `빌런 호석` {

    val reverseCount = listOf(
        listOf(0, 4, 3, 3, 4, 3, 2, 3, 1, 2),
        listOf(4, 0, 5, 3, 2, 5, 6, 1, 5, 4),
        listOf(3, 5, 0, 2, 5, 4, 3, 4, 2, 3),
        listOf(3, 3, 2, 0, 3, 2, 3, 2, 2, 1),
        listOf(4, 2, 5, 3, 0, 3, 4, 3, 3, 2),
        listOf(3, 5, 4, 2, 3, 0, 1, 4, 2, 1),
        listOf(2, 6, 3, 3, 4, 1, 0, 5, 1, 2),
        listOf(3, 1, 4, 2, 3, 4, 5, 0, 4, 3),
        listOf(1, 5, 2, 2, 3, 2, 1, 4, 0, 1),
        listOf(2, 4, 3, 1, 2, 1, 2, 3, 1, 0),
    )

    var answer by Delegates.notNull<Int>()
    var max by Delegates.notNull<Int>()
    var k by Delegates.notNull<Int>()
    var count by Delegates.notNull<Int>()
    var _origin by Delegates.notNull<Int>()


    fun solve() {
        setting()
        findAnswer().also {
            println(it)
        }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            max = this[0]
            k = this[1]
            count = this[2]
            _origin = this[3]
        }
    }

    private fun findAnswer(): Int {
        var answer = 0
        for (_target in 1..max) {
            var cnt = 0
            var origin = _origin
            var target = _target
            if (target == origin) continue

            repeat(k) {
                cnt += reverseCount[origin % 10][target % 10]
                origin /= 10
                target /= 10
            }


            if (cnt <= count) answer++
        }

        return answer
    }
}


fun main() {
    `빌런 호석`().solve()
//    println(35 / 10)
//    println(0 % 10)
}