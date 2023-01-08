package byeonghee.`9week`

import java.io.*
import kotlin.system.exitProcess

class 소병희_java_vs_cpp {
    companion object {
        const val UNDERSCORE = '_'
        const val ERROR = "Error!"

        val br = BufferedReader(InputStreamReader(System.`in`))

        fun solve() {
            val naming = br.readLine()
            val sb = StringBuilder()

            if (naming.first().isLowerCase().not() || naming.last() == '_' ||
                (naming.contains("[A-Z]".toRegex()) && naming.contains('_'))) {
                println("Error!")
                exitProcess(0)
            }

            var prev = ' '
            var cur = ' '
            sb.append(naming.first())
            for (i in 1 until naming.length) {
                prev = naming[i-1]
                cur = naming[i]
                if (prev == UNDERSCORE) {
                    if (cur.isLowerCase()) {
                        sb.append(cur.uppercase())
                    }
                    else error()
                }
                else if (cur.isUpperCase()) {
                    sb.append("_${cur.lowercase()}")
                }
                else if (cur.isLowerCase()) {
                    sb.append(cur)
                }
            }
            println(sb)
        }

        fun error() {
            println(ERROR)
            exitProcess(0)
        }
    }
}

fun main() {
    소병희_java_vs_cpp.solve()
}