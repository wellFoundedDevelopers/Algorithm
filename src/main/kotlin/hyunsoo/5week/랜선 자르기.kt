package hyunsoo.`5week`

/**
 * <문제>
 * [랜선 자르기](https://www.acmicpc.net/problem/1654)
 *
 * N개의 랜던을 만들어야함
 * K개의 랜선이 존재함.
 * 모두 N개의 같은 길이의 랜선으로 만들고 싶어!
 *
 * 아이디어
 * - 이분 탐색
 *  - 1 이 start / 최대가 end
 */
fun main() {

    val (haveCnt, needCnt) = readln().split(" ").map { it.toInt() }

    val lanList = mutableListOf<Long>()

    repeat(haveCnt) {
        val lanInfo = readln().toLong()
        lanList.add(lanInfo)
    }

    var start = 1L
    var end = lanList.maxOrNull()!!
    var maxLan = start

    while (start <= end) {

        val mid = (start + end) / 2

        var canGetLanCnt = 0L
        // 조건
        lanList.forEach { lan ->
            canGetLanCnt += lan / mid
        }

        if (canGetLanCnt >= needCnt) {
            start = mid + 1
            maxLan = mid
        } else {
            end = mid - 1
        }

    }
    println(maxLan)
}