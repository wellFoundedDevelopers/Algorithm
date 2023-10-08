package byeonghee.week47

class 소병희_A와B2 {

    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val s = readLine()
            val t = readLine()

            fun checkAB(sb: StringBuilder): Boolean {
                if (s == sb.toString()) return true
                if (s.length == sb.length) return false

                if (sb.last() == 'A')  {
                    val nsb = StringBuilder(sb)
                    nsb.deleteCharAt(nsb.lastIndex)
                    if (checkAB(nsb)) return true
                }
                if (sb.first() == 'B') {
                    val nsb = StringBuilder(sb)
                    nsb.deleteCharAt(0)
                    nsb.reverse()
                    if (checkAB(nsb)) return true
                }

                return false
            }

            val sb = StringBuilder(t)
            println(if (checkAB(sb)) 1 else 0)
        }
    }
}

fun main() {
    소병희_A와B2.solve()
}