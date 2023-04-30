package jimin.`31week`

class 집합 {
    fun solve() {
        var num = 0
        val sb = StringBuilder()
        repeat(readln().toInt()) {
            val operators = readln().split(" ")
            when (operators.first()) {
                "add" -> {
                    num = num or (1 shl operators.last().toInt() - 1)
                }

                "remove" -> {
                    num = num and (1 shl operators.last().toInt() - 1).inv()
                }

                "check" -> {
                    val tmp = num and (1 shl operators.last().toInt() - 1)
                    if (tmp == 0) sb.append("0\n") else sb.append("1\n")
                }

                "toggle" -> {
                    num = num xor (1 shl operators.last().toInt() - 1)
                }

                "all" -> {
                    num = (1 shl 20).inv()
                }

                "empty" -> {
                    num = 0
                }
            }
        }
        println(sb.toString())
    }
}

fun main() {
    집합().solve()
}