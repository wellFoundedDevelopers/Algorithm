package heejik.`11week`

import kotlin.system.exitProcess

class 왕복 {

    fun solve() {

        val (n, k) = readln().trim().split(' ').map { it.toLong() }
        var s = 0L
        var course = 0
        var isReversed = false

        val length = readln().trim().split(' ').map { it.toLong() }.toMutableList().apply {
            addAll(this.reversed())
        }

        length.forEachIndexed { index, l ->
            s += l
            if (index < n) {
                course++
            } else {
                course--
                isReversed = true
            }

            if (s > k) {
                if (isReversed) course++
                println(course)
                exitProcess(0)
            }
        }

    }
}


fun main() {
    왕복().solve()
}