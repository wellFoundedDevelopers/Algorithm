package byeonghee.week34

import java.io.*

class 소병희_저울 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val m = readLine().toInt()
            val comparable = Array(n + 1) { BooleanArray(n + 1) }
            val sb = StringBuilder()
            var comparableCount = 0

            repeat(m) {
                val (heavy, light) = readLine().split(" ").map { it.toInt() }
                comparable[light][heavy] = true
            }

            for(through in 1 .. n) for(start in 1 .. n) for(end in 1 .. n) {
                if (comparable[start][end]) continue
                if (comparable[start][through] and comparable[through][end])
                    comparable[start][end] = comparable[start][through] and comparable[through][end]
            }

            for(i in 1 .. n) {
                comparableCount = 1
                for(j in 1 .. n) {
                    if (i == j) continue
                    if (comparable[i][j]) comparableCount++
                    if (comparable[j][i]) comparableCount++
                }
                sb.appendLine(n - comparableCount)
            }

            print(sb)
        }
    }
}

fun main() {
    소병희_저울.solve()
}