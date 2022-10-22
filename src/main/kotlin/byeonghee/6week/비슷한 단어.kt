package byeonghee.`6week`

import java.io.*

class `소병희_비슷한 단어` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var answer = 0

        fun solution() {
            val n = br.readLine().toInt()
            val first = br.readLine().toList().sorted().joinToString("")
            val words = Array(n-1) { br.readLine().toList().sorted().joinToString("") }

            words.forEach {
                if (first.length > it.length) compare(it, first) else compare(first, it)
            }

            println(answer)
        }

        fun compare(short: String, long: String) {
            if ((long.length - short.length) > 1) return

            if (long.length > short.length) {
                for(i in long.indices) {
                    if (long.removeRange(i, i + 1) == short) {
                        answer ++
                        return
                    }
                }
                return
            }

            if (short == long) {
                answer ++
                return
            }

            for(i in long.indices) {
                if (long[i] == short[i]) continue

                for(j in i until short.length) {
                    if ((long.drop(i + 1) == short.substring(i, j).plus(short.drop(j+1)))
                        || (short.drop(i + 1) == long.substring(i, j).plus(long.drop(j+1)))) {
                        answer ++
                        return
                    }
                }
                return
            }
        }
    }
}

fun main() {
    `소병희_비슷한 단어`.getSolution().solution()
}