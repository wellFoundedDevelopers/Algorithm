package hyunsoo.`55week`

/**
 *
 * <문제>
 * [세탁소 사장 동혁](https://www.acmicpc.net/problem/2720)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_세탁소_사장_동혁` {

    fun solution() {

        repeat(readln().toInt()) {

            var curMoney = readln().toInt()

            print("${curMoney / 25} ").apply {
                curMoney %= 25
            }
            print("${curMoney / 10} ").apply {
                curMoney %= 10
            }
            print("${curMoney / 5} ").apply {
                curMoney %= 5
            }
            println("$curMoney")


        }
    }
}

fun main() {
    전현수_세탁소_사장_동혁().solution()
}