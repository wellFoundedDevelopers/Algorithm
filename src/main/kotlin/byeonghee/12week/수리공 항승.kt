package byeonghee.`12week`

import java.io.*

class 소병희_수리공항승 {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var answer = 0

        fun solve() {
            val (n, l) = br.readLine().split(" ").map { it.toInt() }
            val leaks = br.readLine().split(" ").map { it.toInt() }.sorted()

            var prevStart = -1
            for(spot in leaks) {
                if (prevStart == -1 || spot - prevStart >= l) {
                    prevStart = spot
                    answer++
                }
            }
            println(answer)
        }
    }
}

fun main() {
    소병희_수리공항승.solve()
}