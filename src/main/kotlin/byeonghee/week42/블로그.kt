package byeonghee.week42

import java.io.BufferedReader
import java.io.InputStreamReader

class 소병희_블로그 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, x) = readLine().split(" ").map { it.toInt() }
            val record = readLine().split(" ").map { it.toInt() }
            var accSum = IntArray(n) { record[it] }

            for(i in 1 until n) {
                accSum[i] += accSum[i-1]
            }

            var best = accSum[x-1]
            var cnt = 1
            var sum = 0

            for(i in x until n) {
                sum = accSum[i] - accSum[i-x]

                if (best == sum) cnt++
                else if (best < sum) {
                    best = sum
                    cnt = 1
                }
            }

            if (best == 0) println("SAD")
            else {
                println("${best}\n${cnt}")
            }
        }
    }
}

fun main() {
    소병희_블로그.solve()
}