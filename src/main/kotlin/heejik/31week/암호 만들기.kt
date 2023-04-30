package heejik.`31week`

import kotlin.properties.Delegates

class `암호 만들기` {

    var l by Delegates.notNull<Int>()
    var c by Delegates.notNull<Int>()
    private lateinit var chars: CharArray
    private val vowels = "aeiou"


    fun solve() {
        setting()
        printAllPasswordCase()
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            l = this[0]
            c = this[1]
        }

        chars = readln()
            .split(' ')
            .sorted()
            .joinToString("")
            .toCharArray()
    }

    private fun printAllPasswordCase(password: CharArray = charArrayOf(), start: Int = 0) {
        if (password.size == l) {
            if (password.count { it in vowels } > 0 && password.count { it !in vowels } > 1) {
                println(password)
            }
            return
        }

        for (i in start until c) {
            printAllPasswordCase(password.plus(chars[i]), start = i + 1)
        }
    }
}

fun main() {
    `암호 만들기`().solve()
}