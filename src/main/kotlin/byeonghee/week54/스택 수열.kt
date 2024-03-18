package byeonghee.week54

class 소병희_스택수열 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val stack = ArrayDeque<Int>(n)
            val sb = StringBuilder()
            var numToPush = 1

            repeat(n) {
                val x = readLine().toInt()

                while (numToPush <= x) {
                    stack.addLast(numToPush++)
                    sb.appendLine("+")
                }
                if (stack.last() == x) {
                    stack.removeLast()
                    sb.appendLine("-")
                }

                else {
                    println("NO")
                    return@with
                }
            }

            println(sb)

        }
}

fun main() {
    소병희_스택수열.solve()
}}