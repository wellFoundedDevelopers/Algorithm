package hyunsoo.`34week`

/**
 *
 * <문제>
 * [Contact](https://www.acmicpc.net/problem/1013)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_Contact` {

    fun solution() {

        val regex = "(100+1+|01)+".toRegex()
        val cnt = readln().toInt()

        repeat(cnt) {
            val target = readln()
            println(
                regex.matchEntire(target)?.let {
                    "YES"
                } ?: "NO"
            )
        }
    }
}

fun main() {
    전현수_Contact().solution()
}