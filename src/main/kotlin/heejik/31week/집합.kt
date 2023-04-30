package heejik.`31week`

import java.util.*

class 집합 {

    enum class Command(val input: String) {
        ADD("add"), REMOVE("remove"), CHECK("check"), TOGGLE("toggle"), ALL("all"), EMPTY("empty")
    }

    val s = mutableSetOf<Int>()
    val sb = StringBuilder()
    fun solve() {
        repeat(readln().toInt()) {
            val input = readln().split(' ')
            val command = input[0]
            val number = input.run {
                if (this.size > 1) {
                    this[1].toInt()
                }
                else Int.MAX_VALUE
            }
            when (command) {
                Command.ADD.input -> s.add(number)
                Command.REMOVE.input -> s.remove(number)
                Command.CHECK.input -> sb.append(if (number in s) "1\n" else "0\n")
                Command.TOGGLE.input -> if (s.remove(number).not()) s.add(number)
                Command.ALL.input -> {
                    s.clear()
                    s.addAll(1..20)
                }
                Command.EMPTY.input -> s.clear()
            }
        }
        println(sb)
    }
}

fun main() {
    집합().solve()
}