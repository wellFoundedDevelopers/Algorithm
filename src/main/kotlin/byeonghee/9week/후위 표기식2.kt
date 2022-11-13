package byeonghee.`9week`

import java.io.*

class 소병희_후위표기식2 {
    companion object {
        const val MUL = '*'
        const val DIV = '/'
        const val ADD = '+'
        const val SUB = '-'

        val br = BufferedReader(InputStreamReader(System.`in`))
        val st = ArrayDeque<Double>()
        val ans = 0L

        fun solve() {
            val n = br.readLine().toInt()
            val expression = br.readLine()
            val nums = Array(n) { br.readLine().toDouble() }

            expression.forEach {
                when (it) {
                    MUL, DIV, ADD, SUB -> calc(st.removeLast(), st.removeLast(), it)
                    else -> st.addLast(nums[it - 'A'])
                }
            }
            println(st.last().let{ String.format("%.2f", it) })
        }

        fun calc(n1: Double, n2: Double, opC: Char) {
            val op: (Double, Double) -> Double =
                when (opC) {
                    MUL -> { b, a -> a * b }
                    DIV -> { b, a -> a / b }
                    ADD -> { b, a -> a + b }
                    SUB -> { b, a -> a - b }
                    else -> { _, _ -> 0.0 }
                }
            st.addLast(op(n1, n2))
        }
    }
}

fun main() {
    소병희_후위표기식2.solve()
}