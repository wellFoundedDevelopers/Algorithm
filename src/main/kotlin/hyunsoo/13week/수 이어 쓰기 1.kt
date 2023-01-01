package hyunsoo.`13week`

/**
 *
 * <문제>
 * [수 이어 쓰기 1](https://www.acmicpc.net/problem/1748)
 *
 * 이슈
 * - 그냥 StringBuilder로 풀었더니 메모리 초과가...
 * - num 을 toString().length 로 계산했더니 메모리 초과가...
 *
 * [아니 과거에 교훈을 얻었다면서 변한게 없냐...](https://soopeach.tistory.com/6)
 *
 * 1 ~ 9 -> 9 개
 * 10 ~ 99 -> 90 개
 * 100 ~ 999 -> 900 개
 * 1000 ~ 9999 -> 9000 개
 * ... 자릿수를 먼저 계산해보자.
 *
 * 자릿수 구하기
 * 9 * 자릿수만큼의 0  / * 자릿수
 * - 한 자릿수는 0, digit = 0
 * - 두 자릿수는 9 * 1, digit = 1
 * - 세 자릿수는 9 * 1 , 90 * 2, .. digit = 2
 * - 네 자릿수는 9 * 1, 90 * 2, 900 * 3 .. digit = 3
 */
fun main() {

    var answer = 0
    val target = readln()
    val targetLength = target.length
    val digit = targetLength - 1

    if (digit == 0) {
        answer = target.toInt()
    } else {
        repeat(digit) { zeroCnt ->
            answer += if (zeroCnt == 0) 9
            else ("9" + "0".repeat(zeroCnt)).toInt() * (zeroCnt + 1)
        }

        answer += (target.toInt() - "9".repeat(digit).toInt()) * targetLength
    }

    println(answer)
}