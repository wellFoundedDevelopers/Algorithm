package hyunsoo.`34week`

/**
 *
 * <문제>
 * [연속된 부분 수열의 합](https://school.programmers.co.kr/learn/courses/30/lessons/178870?language=kotlin)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_연속된_부분_수열의_합` {

    var start = 0
    var end = 0

    var curSum = 0

    fun solution(sequence: IntArray, k: Int): IntArray {

        val answer: IntArray = intArrayOf(0, 0)
        var preAnswerLength = Int.MAX_VALUE

        curSum += sequence[0]

        while (start < sequence.size) {

            if (curSum == k) {
                val curAnswerLength = end - start
                if (curAnswerLength < preAnswerLength) {
                    answer[0] = start
                    answer[1] = end
                    preAnswerLength = curAnswerLength
                }
                slideStart(sequence)
            } else if (curSum < k) {
                if (end < sequence.size - 1) slideEnd(sequence) else slideStart(sequence)
            } else {
                slideStart(sequence)
            }

        }

        return answer
    }

    fun slideStart(target: IntArray) {
        curSum -= target[start]
        start++
    }

    fun slideEnd(target: IntArray) {
        end++
        curSum += target[end]
    }
}

fun main() {
    전현수_연속된_부분_수열의_합().solution(intArrayOf(1, 2, 3, 4, 5), 7)
}