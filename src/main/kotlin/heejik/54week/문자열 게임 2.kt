package heejik.`54week`

import kotlin.math.max
import kotlin.math.min

class `문자열 게임 2` {
    fun solve() {
        val t = readln().toInt()
        val answers = mutableListOf<Pair<Int, Int>>()
        repeat(t) {
            val (word, k) = Array(2) { readln() }
            playGame(word = word, k = k.toInt()).also {
                answers.add(it)
            }
        }

        answers.forEach {
            println(
                if (it.first == Int.MAX_VALUE) -1
                else "${it.first} ${it.second}"
            )
        }
    }

    private fun playGame(word: String, k: Int) : Pair<Int, Int>{
        val idxOfAlphabet = List(26) { emptyList<Int>().toMutableList() }
        var answer3 = Int.MAX_VALUE
        var answer4 = Int.MIN_VALUE
        word.forEachIndexed { index, alphabet ->
            val code = alphabet.code - 97
            idxOfAlphabet[code].add(index)
        }

        // 3
        idxOfAlphabet.forEach {
            if (it.size < k) return@forEach
            for (i in 0..it.size - k) {
                answer3 = min(answer3, it[i + k - 1] - it[i] + 1)
                answer4 = max(answer4, it[i + k - 1] - it[i] + 1)
            }
        }

        return answer3 to answer4
    }
}


fun main() {
    `문자열 게임 2`().solve()
}