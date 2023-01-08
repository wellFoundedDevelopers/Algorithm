package hyunsoo.`1week`

import kotlin.math.*
/*
    요구사항
    - 노란색과 갈색으로 색칠된 격자의 개수로 전체 카펫의 크기를 유추해라.
    - 가로 세로의 크기를 순서대로 배열에 담아 return 할것.

    제한사항
    - brown은 8이상 5,000 이하
    - yellow는 1이상 2,000,000 이하
    - 가로는 세로보다 길거나 같음

    아이디어
    - yellow를 기준으로 모든 경우를 다 체크?
        - ex) 24라면, 1 * 12 / 2 * 6 / 3 * 8 / 4 * 6 이런식으로
        - 만약 y가 3과 같은 수라면?
            - 1 * 3 밖에 안되는거야? 아니면 ㄱ 이렇게 생겨도 되는거야?
    - 즉, yellow 약수의 쌍을 구하고 ((가로 + 2) + 세로) * 2한 것이 brown이 되어야함.
*/
class `카펫_hyunsoo` {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = intArrayOf()

        getDivisorList(yellow).forEach { pair ->
            if(brown == (pair.first + 2 + pair.second) * 2){
                answer += pair.first + 2
                answer += pair.second + 2
            }
        }

        return answer.toList().sortedByDescending{ it }.toIntArray()
    }

    fun getDivisorList(num: Int): MutableList<Pair<Int,Int>>{

        val sqrtOfNum = sqrt(num.toDouble()).toInt()
        val divisorList = mutableListOf<Pair<Int,Int>>()

        for(divider in 1 .. sqrtOfNum){
            if(num % divider == 0) divisorList.add(Pair(divider, num / divider))
        }

        return divisorList
    }
}

fun main(){

    val answer = 카펫_hyunsoo()

    // test 1 -- apply{ this.forEach{ println(it) } } 이 .onEach ...으로 바뀌었음.
    answer.solution(10, 2).onEach { println(it) }
    // test 2
    answer.solution(8, 1).onEach { println(it) }
    // test 3
    answer.solution(24, 24).onEach { println(it) }
}