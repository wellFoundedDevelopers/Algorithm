package hyunsoo.`21week`

/**
 *
 * <문제>
 * [별 찍기 - 13](https://www.acmicpc.net/problem/2523)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_별_찍기_13` {

    fun solution() {
        val cnt = readln().toInt()
        var targetIndex = 1

        while (targetIndex <= cnt) {
            println(STAR.repeat(targetIndex++))
        }
        targetIndex--
        while (1 < targetIndex) {
            println(STAR.repeat(--targetIndex))
        }

    }

    companion object {
        private const val STAR = "*"
    }
}

fun main() {
    전현수_별_찍기_13().solution()
}