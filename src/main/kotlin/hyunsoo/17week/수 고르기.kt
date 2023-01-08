package hyunsoo.`17week`

import java.lang.Integer.min

/**
 *
 * <문제>
 * [수 고르기](https://www.acmicpc.net/problem/2230)
 *
 * N개의 정수로 이루어진 수열 A[N]이 존재한다.
 * - 두 수를 골랐을 때 차이가 M 이상이면서 제일 작은 경우를 구하기
 *
 * - 아이디어
 *
 * 오름차순으로 정렬 후 투포인터 사용하기
 * 두 포인터가 가리키는 숫자의 차이가 M보다 크거나 같다면 start++
 * M보다 작다면 end++
 *
 * 추가적으로 숫자의 차이가 M 이상이면 차이를 갱신
 *
 * - 트러블 슈팅
 * 아 차이가 아니라 합을 구해서 틀렸음..
 *
 */
fun main() {

    val (sequenceSize, target) = readln().split(" ").map { it.toInt() }
    val sequence = mutableListOf<Int>()

    repeat(sequenceSize) {
        sequence.add(readln().toInt())
    }

    sequence.sort()

    var start = 0
    var end = 0
    var minDiff = Int.MAX_VALUE

    while (end <= sequence.lastIndex) {

        val diffOfNums = sequence[end] - sequence[start]

        if (target == diffOfNums) {
            minDiff = min(minDiff, diffOfNums)
            end++
        } else if (target < diffOfNums) {
            minDiff = min(minDiff, diffOfNums)
            start++
        } else {
            end++
        }

    }

    println(minDiff)
}