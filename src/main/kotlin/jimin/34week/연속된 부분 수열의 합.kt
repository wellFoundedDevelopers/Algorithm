package jimin.`34week`

/*
<문제>
[연속된 부분 수열의 합](https://school.programmers.co.kr/learn/courses/30/lessons/178870?language=kotlin)

<구현 방법>
투 포인터 문제!

<트러블 슈팅>
합을 구할 때도 투 포인터를 이용해서~~
*/


class `연속된 부분 수열의 합` {
    fun solution(sequence: IntArray, k: Int): List<Int> {
        var answer: IntArray = intArrayOf()
        var resultStart = 0
        var resultEnd = 0
        var resultLength = Int.MAX_VALUE
        var resultSum = sequence[0]
        var start = 0
        var end = 0

        while (start != sequence.size && end != sequence.size) {
            if (resultSum == k) {
                if (end - start < resultLength) {
                    resultStart = start
                    resultEnd = end
                    resultLength = end - start
                }
                resultSum -= sequence[start]
                start += 1
            } else if (resultSum < k) {
                end += 1
                if (end != sequence.size) {
                    resultSum += sequence[end]
                }
            } else {
                resultSum -= sequence[start]
                start += 1
            }
        }
        return listOf(resultStart, resultEnd)
    }
}
