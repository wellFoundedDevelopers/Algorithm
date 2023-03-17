package byeonghee.week27

import java.io.*

class 소병희_문자열폭발 {

    companion object {
        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            var str = readLine()
            val bomb = readLine()
            val sb = StringBuilder()
            val bombRange = bomb.indices

            fun hasBomb(): Boolean {
                val diff = sb.length - bomb.length
                if (diff < 0) return false

                for (i in bombRange) {
                    if (sb[diff + i] != bomb[i]) return false
                }
                return true
            }

            str.forEach { c ->
                sb.append(c)
                while (hasBomb()) {
                    sb.delete(sb.length - bomb.length, sb.length)
                }
            }

            print(sb.ifBlank { "FRULA" })
        }
    }
}

fun main() {
    소병희_문자열폭발.solve()
}