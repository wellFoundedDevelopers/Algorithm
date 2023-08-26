package heejik.`42week`

class 블로그 {

    fun solve() {
        var answer = 0
        var answerCount = 0

        val (n, x) = readln().split(' ').map { it.toInt() }
        val visitedCount = readln().split(' ').map { it.toInt() }

        val rememberCount = ArrayDeque<Int>()
        var sumOfCount = 0

        visitedCount.forEach { count ->
            if (rememberCount.size == x) {
                sumOfCount -= rememberCount.first()
                sumOfCount += count
                rememberCount.removeFirst()
                rememberCount.addLast(count)
            }
            else if (rememberCount.size < x) {
                rememberCount.addLast(count)
                sumOfCount += count
            }

            if (sumOfCount > answer) {
                answer = sumOfCount
                answerCount = 1
            }
            else if (sumOfCount == answer) answerCount += 1
        }

        print(if (answer == 0) "SAD" else "$answer\n$answerCount")
    }
}


fun main() {
    블로그().solve()
}