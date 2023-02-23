package jimin.`24week`

/*
<문제>
[좋은수열](https://www.acmicpc.net/problem/2661)

<구현 방법>
수열을 1, 2, 3 을 차례로 더하며 만들면서
isGood() 함수를 통해 좋은 수열이면 재귀로 길이가 n이 될때까지 진행한다.
1, 2, 3을 순서대로 숫자를 만들어주고 있으며 재귀로 돌고 있으니
n의 길이가 되는 처음 숫자가 제일 작은 숫자이다.

<트러블 슈팅>
메모리 초과가 엄청나게 났다!!!
구글링해도 로직은 같은데 대체 왜 틀렸을까 고민하다가
코드를 자세히 보니, 무조건 n의 길이가 되는 첫번째 숫자가 젤 작은 숫자인 것을 알게되었다..
그전엔 result를 만들어 작은 값을 리턴해주게 했었다,,,
 */

import kotlin.system.exitProcess

class 좋은수열 {
    private val numbers = mutableListOf("1", "2", "3")
    var n = 0
    fun solve() {
        n = readln().toInt()
        makeNumber(StringBuilder())
    }

    private fun makeNumber(num: StringBuilder) {
        if (num.length == n) {
            println(num)
            exitProcess(0)
        }
        numbers.forEach {
            if (isGood(num.toString() + it)) {
                makeNumber(StringBuilder(num.toString() + it))
            }
        }
    }

    private fun isGood(num: String): Boolean {
        for (i in 1..num.length / 2) {
            if (num.substring(num.length - (i * 2), num.length - i) == num.substring(num.length - i, num.length)) {
                return false
            }
        }
        return true
    }
}

fun main() {
    좋은수열().solve()
}