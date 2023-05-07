package byeonghee.week34

import java.io.*

class 소병희_Contact {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val T = readLine().toInt()
            val vega = "^((100+1+)|(01))+$".toRegex()
            val sb = StringBuilder()

            repeat(T) {
                val wave = readLine()
                when (vega.matches(wave)) {
                    true -> sb.appendLine("YES")
                    else -> sb.appendLine("NO")
                }
            }

            print(sb)
        }
    }
}

fun main() {
    소병희_Contact.solve()
}