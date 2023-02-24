package byeonghee.`24week`

import java.io.*
import kotlin.system.exitProcess


class 소병희_좋은수열 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val nums = arrayOf('1', '2', '3')
            var answer = StringBuilder()

            fun checkBad(len: Int) : Boolean {
                for(l in 2 .. len / 2) {
                    if (answer.substring(len - l, len) == answer.substring(len - l * 2, len - l)) {
                        return true
                    }
                }
                return false
            }

            fun makeSequence(len: Int) {
                if (len == n) {
                    println(answer)
                    exitProcess(0)
                }
                for(x in nums) {
                    if (x == answer.last()) continue
                    answer.append(x)
                    if (checkBad(len + 1).not()) makeSequence(len + 1)
                    answer.deleteCharAt(len)
                }
            }

            for(x in nums) {
                answer.append(x)
                makeSequence(1)
                answer.clear()
            }
        }
    }
}

fun main() {
    소병희_좋은수열.solve()
}