package jimin.`24week`

/*
<문제>
[도서관](https://www.acmicpc.net/problem/1461)

<구현 방법>
책들을 음수와, 양수로 분리한 후 음수도 양수로 만들어 버리고 내림차순으로 정렬한다.
책들을 m개씩 자르면서 자를때 가장 위의 친구의 2배를 result에 더해주었다.
0으로 부터 가장 먼 곳이 맨 마지막에 놔두어야하기 때문에
result의 초기 값을 절댓값이 젤 큰 친구에 -1을 곱해 두었다.

<트러블 슈팅>
 */

import kotlin.math.*

class 도서관 {
    fun solve() {
        val (_, m) = readln().split(" ").map { it.toInt() }
        val books = readln().split(" ").map { it.toInt() }.sorted()
        var result = books.maxOf { abs(it) } * -1

        result += getWalkCount(books.filter { it < 0 }.map { it * (-1) }, m)
        result += getWalkCount(books.filter { it > 0 }.reversed(), m)

        println(result)
    }

    private fun getWalkCount(list: List<Int>, m: Int): Int {
        var result = 0
        var books = list
        while (books.isNotEmpty()) {
            if (books.size >= m) {
                result += books.first() * 2
                books = books.drop(m)
            } else {
                result += books.first() * 2
                break
            }
        }
        return result
    }
}

fun main() {
    도서관().solve()
}