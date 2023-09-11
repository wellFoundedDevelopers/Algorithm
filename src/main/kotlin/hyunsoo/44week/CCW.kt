package hyunsoo.`44week`

/**
 *
 * <문제>
 * [CCW](https://www.acmicpc.net/problem/11758)
 *
 * - 아이디어
 *
 * [참고](https://velog.io/@jini_eun/%EB%B0%B1%EC%A4%80-11758%EB%B2%88-CCW-Java-Python)
 *
 * x1 x2 x3 x4
 * y1 y2 y3 y4
 * - 트러블 슈팅
 *
 */
class `전현수_CCW` {

    private data class Point(val x: Int, val y: Int)

    fun solution() {
        val p = Array(3) {
            readln().split(" ")
                .map { it.toInt() }
                .run {
                    Point(first(), last())
                }
        }

        val first = (0..2).run {
            var triangle = 0
            this.forEach { index ->
                triangle += p[index % 3].x * p[(index + 1) % 3].y
            }
            triangle
        }

        val second = (1..3).run {
            var triangle = 0
            this.forEach { index ->
                triangle += p[index % 3].y * p[(index + 1) % 3].x
            }
            triangle
        }

        val answer = first - second
        println(
            if (0 < answer) {
                1
            } else if (answer < 0) {
                -1
            } else {
                0
            }
        )

    }
}

fun main() {
    전현수_CCW().solution()
}