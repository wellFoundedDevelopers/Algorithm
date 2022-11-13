package byeonghee.`9week`

import java.io.*
import kotlin.math.abs

class 소병희_도어맨 {
    companion object {
        const val M = 1
        const val W = -1

        val br = BufferedReader(InputStreamReader(System.`in`))
        var count = 0

        fun solve() {
            val d = br.readLine().toInt()
            val wait = br.readLine().map { if (it == 'M') M else W }
            wait.indexOfFirst {
                count += it
                abs(count) > d + 1
            }.let { ans -> when (ans) {
                -1 -> if (abs(count) == d + 1) println(wait.size - 1) else println(wait.size)
                else -> println(ans - 1)
            } }
        }
    }
}

fun main() {
    소병희_도어맨.solve()
}