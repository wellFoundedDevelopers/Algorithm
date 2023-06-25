package byeonghee.week41

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

class 소병희_개미의이동 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (l, t) = readLine().split(" ").map { it.toInt() }
            val n = readLine().toInt()
            val ants = IntArray(n)
            val sb = StringBuilder()

            for(i in 0 until n) {
                val far = abs(readLine().split(" ").let {
                    it[0].toInt() * (if (it[1] == "D") 1 else -1)
                } + t)

                ants[i] = if (far / l % 2 == 0) far % l else l - far % l
            }

            ants.sort()
            ants.forEach { sb.append("$it ") }
            println(sb)
        }
    }
}

fun main() {
    소병희_개미의이동.solve()
}