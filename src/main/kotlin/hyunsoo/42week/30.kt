package hyunsoo.`42week`

import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [30](https://www.acmicpc.net/problem/10610)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_30` {

    fun solution() {
        val num = readln()

        num.fold(0) { total, next ->
            total + (next - '0')
        }.apply {
            if (this % 3 != 0 || num.contains('0').not()) {
                println(-1)
                exitProcess(0)
            }
        }

        num.toList().sortedByDescending { it }.joinToString("").apply {
            println(this)
        }
    }
}

fun main() {
    전현수_30().solution()
}