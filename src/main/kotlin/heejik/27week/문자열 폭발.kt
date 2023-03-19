package heejik.`27week`

import kotlin.properties.Delegates

class `문자열 폭발` {
    private lateinit var base: CharArray
    private lateinit var bombS: String
    private val notBombed = StringBuilder()
    private var bombSize by Delegates.notNull<Int>()

    fun solve() {
        setting()
        bomb()
    }

    private fun setting() {
        base = readln().toCharArray()
        bombS = readln()
        bombSize = bombS.length
    }

    private fun bomb() {
        var cnt = 0
        while (cnt != base.size) {
            notBombed.append(base[cnt])
            check()
            cnt++
        }

        println(notBombed.toString().ifEmpty { "FRULA" })
    }

    private fun check() {
        if (notBombed.takeLast(bombSize) == bombS) {
            repeat(bombSize) {
                notBombed.deleteCharAt(notBombed.length - 1)
            }
        }
    }
}

fun main() {
    `문자열 폭발`().solve()
}