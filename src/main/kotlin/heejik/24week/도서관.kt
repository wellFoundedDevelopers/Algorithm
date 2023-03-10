package heejik.`24week`

import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.properties.Delegates

class 도서관 {

    data class Book(
        val position: Int
    )

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    private val leftBooks = mutableListOf<Book>()
    private val rightBooks = mutableListOf<Book>()
    private fun getInputIntList() = readln().split(' ').map { it.toInt() }

    fun solve() {
        setting()
        clean()
    }

    private fun setting() {
        getInputIntList().run {
            n = this[0]
            m = this[1]
        }
        getInputIntList().forEach {
            if (it < 0) {
                leftBooks.add(Book(position = it))
            } else {
                rightBooks.add(Book(position = it))
            }
        }
        leftBooks.sortBy { it.position }
        rightBooks.sortByDescending { it.position }
    }

    private fun clean() {
        val longestPos = max(
            leftBooks.maxOfOrNull { it.position.absoluteValue } ?: 0,
            rightBooks.maxOfOrNull { it.position.absoluteValue } ?: 0
        )
        println(putBack(leftBooks) + putBack(rightBooks) - longestPos)
    }

    private fun putBack(books: MutableList<Book>): Int {
        var distance = 0
        while (books.isNotEmpty()) {
            if (books.size >= m) {
                val holdingBooks = books.subList(0, m)
                distance += holdingBooks.first().position
                books.removeAll(holdingBooks)
            } else {
                distance += books.maxOf { it.position.absoluteValue }
                books.clear()
            }
        }
        return distance * 2
    }
}

fun main() {
    도서관().solve()
}