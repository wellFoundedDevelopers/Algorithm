package byeonghee.`4week`

import java.io.*

class `소병희_보물` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var N = 0
        var A = listOf<Int>()
        var B = listOf<Int>()
        var answer = 0

        fun solution() {
            N = br.readLine().toInt()
            A = br.readLine().split(" ").map{ it.toInt() }.sorted()
            B = br.readLine().split(" ").map{ it.toInt() }.sortedDescending()

            for(i in 0 until N) { answer += A[i] * B[i] }
            println(answer)
        }
    }
}

fun main() {
    `소병희_보물`.getSolution().solution()
}