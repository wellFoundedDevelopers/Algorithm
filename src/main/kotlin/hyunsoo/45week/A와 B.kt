package hyunsoo.`45week`

/**
 *
 * <문제>
 * [A와 B](https://www.acmicpc.net/problem/12904)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_A와_B` {

    fun solution() {
        val initString = readln()
        var targetString = readln()

        while (initString.length != targetString.length) {

            if (targetString.last() == 'A') {
                targetString = targetString.substring(0, targetString.length - 1)
            } else {
                targetString = targetString.substring(0, targetString.length - 1)
                targetString = targetString.reversed()
            }
        }

        println(
            if (initString == targetString) 1 else 0
        )
    }
}

fun main() {
    전현수_A와_B().solution()
}