package byeonghee.week31

import java.io.*

class 소병희_집합 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val m = readLine().toInt()
            val set = IntArray(20)
            val sb = StringBuilder()
            var op: String
            var x = 0

            for(i in 0 until m) {
                readLine().split(" ").let {
                    op = it[0]
                    if (it.size > 1) x = it[1].toInt() - 1
                }

                when(op) {
                    "add" -> set[x] = 1
                    "remove" -> set[x] = 0
                    "check" -> sb.appendLine(set[x])
                    "toggle" -> set[x] = 1 - set[x]
                    "all" -> set.fill(1)
                    "empty" -> set.fill(0)
                }
            }

            print(sb)
        }
    }
}

fun main() {
    소병희_.solve()
}