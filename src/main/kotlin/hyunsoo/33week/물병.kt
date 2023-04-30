package hyunsoo.`33week`


/**
 *
 * <문제>
 * [물병](https://www.acmicpc.net/problem/1052)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_물병` {

    fun solution() {
        var (n, k) = readln().split(" ").map { it.toInt() }

        var ans = 0

        while (n.toString(2).count { it == '1' } > k) {
            n += 1
            ans++
        }

        println(ans)

    }
}


fun main() {
    전현수_물병().solution()
}