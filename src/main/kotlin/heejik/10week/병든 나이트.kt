package heejik.`10week`

import kotlin.math.min

class `병든 나이트` {

    fun solve() {
        val (n, m) = readln().split(' ').map { it.toInt() }

        println(
            if (n == 1) {
                1
            } else if (n == 2) {
                min(4, (m + 1) / 2)
            } else {
                if (m <= 4) {
                    m
                } else if (m == 5) {
                    4
                } else {
                    m - 2
                }
            }
        )
    }
}


fun main() {
    `병든 나이트`().solve()
}