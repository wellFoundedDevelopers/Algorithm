package heejik.`9week`

class `후위 표기식2` {

    val operands = mutableListOf<Double>()

    fun solve() {
        val n = readln().toInt()
        val postfix = readln().chunked(1).toMutableList()

        repeat(n) { cnt ->
            val num = readln()
            postfix.replaceAll {
                if (it.contains('A' + cnt)) num
                else it
            }
        }

        postfix.forEach {
            if (it.toIntOrNull() != null) {
                operands.add(it.toDouble())
            } else {
                val a = operands.removeLast()
                val b = operands.removeLast()
                when (it.first()) {
                    '*' -> operands.add(b * a)
                    '+' -> operands.add(b + a)
                    '/' -> operands.add(b / a)
                    '-' -> operands.add(b - a)
                }
            }
        }
        println(String.format("%.2f", operands.first()))
    }
}

fun main() {
    `후위 표기식2`().solve()
}