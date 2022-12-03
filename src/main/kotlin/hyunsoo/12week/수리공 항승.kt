package hyunsoo.`12week`

/**
 * <문제>
 * [수리공 항승](https://www.acmicpc.net/problem/1449)
 *
 * 물새는 곳을 하나씩 막아보자앗..!!
 */
fun main() {

    val (_, tapeLength) = readln().split(" ").map { it.toInt() }
    val leakPositionList = readln().split(" ")
        .map { it.toInt() }
        .sorted()

    var lastTapedPosition = leakPositionList.first() + tapeLength - 0.5f
    var tapedCnt = 1

    leakPositionList.drop(1).forEach { leakPosition ->
        if (lastTapedPosition < leakPosition) {
            lastTapedPosition = leakPosition + tapeLength - 0.5f
            tapedCnt++
        }
    }

    println(tapedCnt)
}