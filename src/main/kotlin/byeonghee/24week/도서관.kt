package byeonghee.`24week`

import java.io.*


class 소병희_도서관 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val books = readLine().split(" ").map { it.toInt() }.sorted()
            var answer = 0

            fun orderBooks(i: Int, mv: Int) {
                var p = i
                while(p in 0 until n && books[p] * mv < 0) {
                    answer -= books[p] * 2 * mv
                    p += mv * m
                }
            }

            orderBooks(0, 1)
            orderBooks(n - 1, -1)

            answer +=  if (books.first() + books.last() > 0) -1 * books.last() else books.first()
            println(answer)
        }
    }
}

fun main() {
    소병희_도서관.solve()
}