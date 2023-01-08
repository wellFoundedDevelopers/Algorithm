package hyunsoo.`7week`

/**
 * <문제>
 * [흙길 보수하기](https://www.acmicpc.net/problem/1911)
 *
 * 범위 보니까 이거 이분 탐색이네??...
 * -> 아니네...
 *
 * 스위핑?
 *
 * 입/출력
 * - 첫째 줄에 물웅덩이의 개수(N)과 물웅덩이를 덮을 수 있는 널빤지의 길이(L)가 주어짐.
 * - 웅덩이의 정보가 주어짐. 웅덩이의 정보는 웅덩이의 시작 위치와 끝 위치로 이루어짐.
 */

data class Puddle(val start: Int, val end: Int)

fun main() {

    val (puddleCnt, plankLength) = readln().split(" ").map { it.toInt() }
    // 널빤지의 끝 위치가 담길 변수
    var lastPlankLocation = 0
    var needPlankCnt = 0

    val puddleArray = Array(puddleCnt) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        Puddle(start, end)
    }

    puddleArray.sortWith(compareBy<Puddle> { it.start }.thenBy { it.end })


    puddleArray.forEach { puddle ->
        lastPlankLocation = if (puddle.start < lastPlankLocation) lastPlankLocation else puddle.start
        val end = puddle.end

        while (lastPlankLocation < end) {
            lastPlankLocation += plankLength
            needPlankCnt++
        }
    }

    println(needPlankCnt)
}