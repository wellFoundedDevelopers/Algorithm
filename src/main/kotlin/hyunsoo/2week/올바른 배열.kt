package hyunsoo.`2week`

import java.lang.Math.min


/**
 *
 * 올바른 배열 == 어떤 배열 속에 있는 원소중 5개가 연속적인 것을 뜻함.
 * - 연속적인 것이란? 5개의 수를 정렬했을 때, 인접한 수의 차이가 1인 것!
 *
 * 아이디어
 * - 모든 원소를 하나씩 탐색하기,
 *      - 탐색중인 원소 ~ 탐색중인 원소 + 5가 올바른 배열
 *      - 위의 올바른 배열을 리스트로 만들고 순차 탐색하며 몇개가 이미 원소 리스트에 존재하는지 확인
 *
 */
fun main() {

    val elementCnt = readln().toInt()
    val elementList = mutableListOf<Int>()
    var cntToNeed = 4

    repeat(elementCnt) {
        val num = readln().toInt()
        elementList.add(num)
    }

    elementList.sort()

    elementList.forEach { startNum ->

        val temp = startNum until startNum + 5
        var insufficentCnt = 5

        temp.forEach { curNum ->
            if (elementList.contains(curNum)) insufficentCnt--
        }

        cntToNeed = min(insufficentCnt, cntToNeed)
    }

    println(cntToNeed)

}