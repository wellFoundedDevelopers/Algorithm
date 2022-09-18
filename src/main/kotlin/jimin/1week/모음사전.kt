package jimin.`1week`

/*
<문제>
https://school.programmers.co.kr/learn/courses/30/lessons/84512

<구현 방법>
"A", "E", "I", "O", "U", "" 의 배열을 모두 돌 수 있는 6중 for문을 만듬.
mutableSet을 이용하여 중복을 제거해주고, filter를 이용해 길이가 5까지인 숫자만 필터링한 후 정렬하여 몇번째 단어인지 찾음.

<트러블 슈팅>
너무 시간이 많이 걸리는 풀이인 것 같음..

 */

class `모음사전_jimin` {
    fun solution(word: String): Int {
        return order(word)
    }

    fun order(word: String): Int {
        val list = arrayListOf("A", "E", "I", "O", "U", "")
        var total = mutableSetOf<String>()

        for (x1 in list) {
            for (x2 in list) {
                for (x3 in list) {
                    for (x4 in list) {
                        for (x5 in list) {
                            for (x6 in list) {
                                val newWord = x1 + x2 + x3 + x4 + x5 + x6
                                total.add(newWord)
                            }
                        }
                    }
                }
            }
        }
        val sortedTotal = total.filter { it.length < 6 }.sorted()
        //println(sortedTotal)

        return sortedTotal.indexOf(word)
    }
}