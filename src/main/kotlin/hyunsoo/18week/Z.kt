package hyunsoo.`18week`

import java.lang.Math.pow

/**
 *
 * <문제>
 * [Z](https://www.acmicpc.net/problem/1074)
 *
 * - 아이디어
 * 구현을 해보려고 했는데 2의 14승 * 2의 14승은 2의 28승임... <- 대략 2억 6천 ...
 *
 * 2의 2n승 - 2
 * - 트러블 슈팅
 * 둘 다 0이 아닐 때만 while문을 돌렸을 경우에 0 0, 0 1, 1 0 등의 경우는
 * 루프 자체를 돌지 않아서 탐색이 되자 않았음.
 *
 */

fun main() {

    var (n, row, column) = readln().split(" ").map { it.toInt() }

    var size = powerOfTwo(n)
    var cnt = 0

    while (row != 0 || column != 0) {

        size /= 2

        val quotientOfRow = row / size
        val quotientOfCol = column / size

        // 좌상
        if (quotientOfRow == 0 && quotientOfCol == 0) {
            // 우상
        } else if (quotientOfRow == 0 && quotientOfCol == 1) {
            cnt += size * size * 1
            // 좌하
        } else if (quotientOfRow == 1 && quotientOfCol == 0) {
            cnt += size * size * 2
            // 우하
        } else if (quotientOfRow == 1 && quotientOfCol == 1) {
            cnt += size * size * 3
        }

        row %= size
        column %= size

    }

    println(cnt)

}

fun powerOfTwo(power: Int): Int {
    return pow(2.0, power.toDouble()).toInt()
}

