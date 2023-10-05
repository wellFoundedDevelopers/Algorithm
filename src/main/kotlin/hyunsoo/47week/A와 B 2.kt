package hyunsoo.`47week`

import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [A와 B 2](https://www.acmicpc.net/problem/12919)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_A와_B_2` {

    fun solution() {

        val s = readln()
        val t = readln()

        find(s, t)

        println(0)
    }

    fun find(s: String, t: String) {

        if (s.length == t.length) {

            if (s == t) {
                println(1)
                exitProcess(0)
            }

            return
        }

        if (t.last() == 'A') {
            find(s, t.substring(0, t.lastIndex))
        }
        if (t.first() == 'B') {
            find(s, t.substring(1, t.lastIndex + 1).reversed())
        }

    }
}

fun main() {
    전현수_A와_B_2().solution()
}