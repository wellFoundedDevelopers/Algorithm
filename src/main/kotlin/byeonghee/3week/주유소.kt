package byeonghee.`3week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Long.min

class `소병희_주유소` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var N = 0
        var minPrice = 1234567890L
        var minCost = 0L

        fun solution() {
            N = br.readLine().trim().toInt()
            val distances = br.readLine().trim().split(" ").map{ it.toInt() }
            val prices = br.readLine().trim().split(" ").map{ it.toLong() }

            for(i in 0 until N-1) {
                minPrice = min(minPrice, prices[i])
                minCost += minPrice * distances[i]
            }

            println(minCost)
        }
    }
}

fun main() {
    `소병희_주유소`.getSolution()
}