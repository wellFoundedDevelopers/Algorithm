package byeonghee.`1week`

import kotlin.math.*

class Solution_byeonghee {
    val priority = "AEIOU"
    val base = 5F

    fun solution(word: String): Int {
        var answer = 0

        word.forEachIndexed { preDigit, letter ->
            val preAlpha = priority.indexOf(letter)
            val geoSeq = ( base.pow(base - preDigit) - 1 ) / 4
            answer += preAlpha * geoSeq.toInt() + 1
        }

        return answer
    }
}

fun main() {
    val sol = Solution_byeonghee()

    val words = arrayOf("AAAAE", "AAAE", "I", "EIO")
    val answers = arrayOf(6, 10, 1563, 1189)

    for(i in 0 until 4) {
        println(String.format("%-7s 내답: %-7d 정답: %-7d", words[i], sol.solution(words[i]), answers[i]))
    }
}