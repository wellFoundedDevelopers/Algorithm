package hyunsoo.`1week`

import java.lang.Math.sqrt

/**
 *
 * 요구사항
 * - M, N이 주어지면 소수인 것들을 골라 소수의 합과 최솟값을 찾기
 *
 * 간단히 소수를 구하면 되겠다.
 * n이 소수인지 판별하는 법
 *  - 2 ~ n-1까지 나눠보기 / 나누어 떨어지는 수가 없으면 소수
 *  - 2 ~ n의 제곱근까지 나눠보기 / 나누어 떨어지는 수가 없으면 소수
 *  - 에라토스테네스의 체
 *
 *  이슈
 *  - 1은 소수가 아님
 *  - 2를 제외한 모든 짝수는 소수가 아님
 *
 */
fun main() {

    val start = readln().toInt()
    val end = readln().toInt()
    val primeList = mutableListOf<Int>()

    for (curNum in start..end) {
        if (isPrime(curNum)) primeList.add(curNum)
    }

    if (primeList.isEmpty()) println(-1)
    else {
        primeList.sum().apply { println(this) }
        primeList.first().apply { println(this) }
    }

}

fun isPrime(num: Int): Boolean {

    if (num == 1) return false
    if (num % 2 == 0 && num != 2) return false

    val squareRoot = sqrt(num.toDouble()).toInt()
    for (divider in 2..squareRoot) {
        if (num % divider == 0) return false
    }

    return true
}