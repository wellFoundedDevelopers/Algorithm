package byeonghee.`5week`

/**
 * @접근방식: 무지성 3중 for문 + 복붙!
 */

import java.io.*

class `소병희_바닥장식` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        companion object {
            const val CHECKED = '.'
            const val HORIZONTAL = '-'
            const val VERTICAL = '|'
        }

        val br = BufferedReader(InputStreamReader(System.`in`))
        var answer = 0
        var floor = Array(0) { CharArray(0) }

        fun solution() {
            val (n, m) = br.readLine().split(" ").map { it.toInt() }
            floor = Array(n) { br.readLine().toCharArray() }

            for(i in 0 until n) for(j in 0 until m) {
                when(floor[i][j]) {
                    CHECKED -> continue
                    HORIZONTAL -> {
                        answer++
                        for(c in j until m) {
                            if (floor[i][c] != HORIZONTAL) break
                            floor[i][c] = CHECKED
                        }
                    }
                    VERTICAL -> {
                        answer++
                        for(r in i until n) {
                            if (floor[r][j] != VERTICAL) break
                            floor[r][j] = CHECKED
                        }
                    }
                }
            }

            println(answer)
        }

    }
}

fun main() {
    `소병희_바닥장식`.getSolution().solution()
}