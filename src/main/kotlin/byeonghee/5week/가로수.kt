package byeonghee.`5week`

import java.io.*

/**
 * 접근방식: gcd
 */

class `소병희_가로수` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        private val br = BufferedReader(InputStreamReader(System.`in`))

        private lateinit var trees : IntArray
        private lateinit var distances : IntArray

        private var curGcd = 0

        private fun gcd(a: Int, b: Int) : Int {
            if (b == 0) return a
            return gcd(b, a % b)
        }

        fun solution() {
            val n = br.readLine().toInt()
            trees = IntArray(n)
            distances = IntArray(n-1)

            trees[0] = br.readLine().toInt()
            repeat(n-1) {
                trees[it+1] = br.readLine().toInt()
                distances[it] = trees[it+1] - trees[it]
                curGcd = gcd(distances[it], curGcd)
            }

            println(distances.sumOf { it / curGcd - 1 })
        }
    }
}

fun main() {
    `소병희_가로수`.getSolution().solution()
}