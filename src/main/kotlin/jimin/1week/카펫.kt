package jimin.`1week`

/*
<문제>
https://school.programmers.co.kr/learn/courses/30/lessons/42842

<구현 방법>
yellow의 약수를 찾고, 약수들 중에서 brown의 조건을 만족하는 숫자를 찾는다.
이때 약수는 제곱근(sqrt(double or float))을 사용하면 적은 시간 복잡도를 가질 수 있다.

<트러블 슈팅>
IntArray를 바로 출력하면 값이 해시값으로 보임.
=> 배열.contentToString()을 하면 배열이 잘 출력된다.

 */

import kotlin.math.*

class `카펫_jimin` {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = divide(brown, yellow)
        answer = answer.sorted().reversed().toIntArray()

        return answer
    }

    private fun divide(brown: Int, yellow: Int): IntArray {
        for (i in 1..sqrt(yellow.toDouble()).toInt()) {
            if (yellow % i == 0) {
                if (brown == (i + 2) * 2 + (yellow / i) * 2) {
                    return intArrayOf(i + 2, yellow / i + 2)
                }
            }
        }
        return intArrayOf()
    }
}

fun main() {
    val test = `카펫_jimin`()
    println(test.solution(10,2).contentToString())
    println(test.solution(8,1).contentToString())
    println(test.solution(24,24).contentToString())
}