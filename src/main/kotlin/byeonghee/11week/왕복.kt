package byeonghee.`11week`

import java.io.*
import kotlin.system.exitProcess

class 소병희_ {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var n = 0
        var k = 0L

        fun solve() {
            br.readLine().split(" ").let {
                n = it.first().toInt()
                k = it.last().toLong()
            }
            val course = br.readLine().split(" ").map { it.toLong() }.let { it.plus(it.reversed())}
            course.foldIndexed(0L) { i, acc, v ->
                if (acc + v > k) {
                    println(if (i < n) i + 1 else (2 * n - i))
                    exitProcess(0)
                }
                acc + v
            }
        }
    }
}

fun main() {
    소병희_.solve()
}