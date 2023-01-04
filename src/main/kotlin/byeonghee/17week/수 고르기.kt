package byeonghee.`17week`

import java.io.*

class 소병희_수고르기 {

    companion object {
        lateinit var seq : List<Int>
        var start = 0
        var answer = 0

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            seq = List(n) { readLine().toInt() }.sorted()
            answer = seq.last() - seq.first()

            for (i in 1 until n) {
                while (start < i - 1 && seq[i] - seq[start + 1] >= m) {
                    start++
                }
                if (seq[i] - seq[start] >= m) {
                    answer = answer.coerceAtMost(seq[i] - seq[start])
                }
            }
            println(answer)
        }
    }
}

fun main() {
    소병희_수고르기.solve()
}