package heejik.`31week`

import kotlin.properties.Delegates

class 숫자고르기 {
    var n by Delegates.notNull<Int>()
    val numbers = mutableListOf<Int>()
    var answers = mutableSetOf<Int>()
    fun solve() {
        setting()
        getAnswer()
        println(answers.size)
        answers.sorted().forEach {
            println(it + 1)
        }
    }

    private fun setting() {
        n = readln().toInt()
        repeat(n) { idx ->
            val number = readln().toInt() - 1
            numbers.add(number)
        }
    }

    private fun getAnswer() {
        repeat(n) { idx ->
            val selectedIdxNumbers = mutableListOf<Int>()
            val selectedValueNumbers = mutableListOf<Int>()
            var tmp = idx
            while (true) {
                selectedIdxNumbers.add(tmp)
                tmp = numbers[tmp]
                selectedValueNumbers.add(tmp)
                if (tmp in selectedIdxNumbers) break
            }
            if (selectedIdxNumbers.all { it in selectedValueNumbers }) {
                selectedIdxNumbers.forEach {
                    answers.add(it)
                }
            }
        }
    }
}

fun main() {
    숫자고르기().solve()
}