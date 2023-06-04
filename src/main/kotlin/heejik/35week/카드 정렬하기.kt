package heejik.`35week`

import java.util.PriorityQueue

class `카드 정렬하기` {

    private val cards = PriorityQueue<Int>()

    fun solve() {
        input()
        var answer = 0

        while (cards.size > 1) {
            val first = cards.poll()
            val second = cards.poll()

            cards.add(first + second)
            answer += first + second
        }

        println(answer)
    }

    fun input() {
        repeat(readln().toInt()) {
            val card = readln().toInt()
            cards.add(card)
        }
    }
}


fun main() {
    `카드 정렬하기`().solve()
}