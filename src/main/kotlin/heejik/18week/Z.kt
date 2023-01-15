package heejik.`18week`

import kotlin.math.pow

class Z {

    var n = 0
    var r = 0
    var c = 0
    var answer = -1L
    var cnt = 0L
    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            r = this[1]
            c = this[2]
        }
        rec(n + 1, 0, 0)

        if (r == 0 && c == 0) {
            println(0)
        } else {
            println(answer - 1)
        }
    }

    fun rec(offset: Int, x: Int, y: Int) {
        if (offset == 0) return
        if (offset == 1) cnt++


        if (x == r && y == c) {
            answer = cnt
        }

        val distance = (2.0).pow(offset - 2).toInt()

        if (r in x..x + distance && c in y..y + distance) {
            rec(offset - 1, x, y)       // 1
        } else {
            cnt += distance * distance
        }
        if (r in x..x + distance && c in y + distance..y + distance * 2) {
            rec(offset - 1, x, y + distance)       // 2
        } else {
            cnt += distance * distance
        }
        if (r in x +distance..x + distance * 2 && c in y..y + distance) {
            rec(offset - 1, x + distance, y)       // 3
        } else {
            cnt += distance * distance
        }
        if (r in x +distance..x + distance * 2 && c in y + distance..y + distance * 2) {
            rec(offset - 1, x + distance, y + distance)       // 4
        } else {
            cnt += distance * distance
        }
    }
}

fun main() {
    Z().solve()

}