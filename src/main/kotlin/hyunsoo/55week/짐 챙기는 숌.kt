package hyunsoo.`55week`

/**
 *
 * <문제>
 * [짐 챙기는 숌](https://www.acmicpc.net/problem/1817)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_짐_챙기는_숌` {

    fun solution() {

        val (bookCnt, maxWeight) = readln().split(" ").map { it.toInt() }

        if (bookCnt == 0) {
            println(0)
            return
        }

        val books = readln().split(" ").map { it.toInt() }

        var boxCnt = 0
        var curBoxWeight = 0

        for (index in books.indices) {

            val curBook = books[index]
            if (maxWeight < curBoxWeight + curBook) {
                boxCnt++
                curBoxWeight = curBook
            } else {
                curBoxWeight += curBook
            }
        }

        if (0 < curBoxWeight) boxCnt++

        println(boxCnt)

    }
}

fun main() {
    전현수_짐_챙기는_숌().solution()
}