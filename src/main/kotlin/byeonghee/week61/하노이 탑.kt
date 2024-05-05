package byeonghee.week61

class 소병희_하노이탑 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val sb = StringBuilder()

            fun buildTower(x: Int, from: Int, to: Int, rest: Int) {
                if (x > 1) buildTower(x-1, from, rest, to)
                sb.appendLine("$from $to")
                if (x > 1) buildTower(x-1, rest, to, from)
            }

            if (n < 64) {
                val k = (1L shl n) - 1
                sb.appendLine(k)
                if (n <= 20) buildTower(n, 1, 3, 2)
            }
            else {
                val digits = IntArray(32)

                digits[0] = 1
                var over = 0
                var last = 0

                for(i in 1 .. n) {
                    over = 0

                    for(d in 0 .. last) {
                        digits[d] = digits[d] * 2 + over
                        over = digits[d] / 10
                        digits[d] %= 10
                    }

                    if(over > 0) {
                        last++
                        digits[last] = over
                    }
                }

                for(i in 0 .. last) {
                    if (digits[i] > 0) {
                        digits[i]--
                        break
                    }
                    digits[i] = 9
                }

                for(i in last downTo 0) {
                    sb.append(digits[i])
                }
            }

            println(sb)
        }
    }
}

fun main() {
    소병희_하노이탑.solve()
}