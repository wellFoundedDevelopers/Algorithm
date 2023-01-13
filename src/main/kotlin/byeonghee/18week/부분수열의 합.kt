package byeonghee.`18week`

import java.io.*
import kotlin.math.pow

class 소병희_부분수열의합 {

    companion object {
        lateinit var seq : List<Int>
        var n = 0
        var s = 0
        var total = 0
        var answer = 0

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (inN, inS) = readLine().split(" ").map { it.toInt() }
            seq = readLine().split(" ").map { it.toInt().also { total += it } }
            n = inN
            s = inS

            for ( i in 0 until 2.0.pow(n - 1).toInt()) {
                calcBitmask(i)
            }

            println(if (s == 0) answer - 1 else answer)
        }

        fun calcBitmask(i: Int) {
            var x = i
            var p = 0
            var ans = 0

            while (x > 0) {
                if (x % 2 == 1) ans += seq[p]
                x /= 2
                p++
            }

            if (ans == s) answer++
            if (total - ans == s) answer++
        }
    }
}

fun main() {
    소병희_부분수열의합.solve()
}