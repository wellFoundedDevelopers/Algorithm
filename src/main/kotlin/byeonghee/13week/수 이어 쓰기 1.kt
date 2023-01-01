package byeonghee.`13week`

import java.io.*
import kotlin.math.pow

class 소병희_수이어쓰기1 {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))

        fun solve() {
            val n = br.readLine().toInt()
            var i = 1
            var answer = 0

            while (n >= powDecimal(i)) {
                answer += i * (powDecimal(i) - powDecimal(i - 1)) + 1
                i++
            }
            answer += i * (n - powDecimal(i - 1)) + 1
            println(answer)
        }

        fun powDecimal(i : Int) : Int {
            return 10.0.pow(i).toInt()
        }
    }
}

fun main() {
    소병희_수이어쓰기1.solve()
}