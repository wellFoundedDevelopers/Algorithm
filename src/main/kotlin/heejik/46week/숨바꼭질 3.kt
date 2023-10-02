package heejik.`46week`

import kotlin.math.min
import kotlin.properties.Delegates

class `숨바꼭질 3` {


    val visited = MutableList(100001) { Int.MAX_VALUE }
    var n by Delegates.notNull<Int>()
    var k by Delegates.notNull<Int>()
    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            k = this[1]
        }

        bfs(n).also {
            println(it)
        }
    }

    fun bfs(start: Int): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(start to 0)
        visited[start] = 0

        var answer = Int.MAX_VALUE

        while (queue.isNotEmpty()) {
            val (number, time) = queue.removeFirst()
            if (number == k) {
                answer = min(answer, time)
            }

            var multiNumber = number * 2
            while (multiNumber <= 100000) {
                if (visited[multiNumber] < time) break
                queue.addFirst(multiNumber to time)
                visited[multiNumber] = time
                multiNumber *= 2
            }

            val upNumber = number + 1
            val downNumber = number - 1
            if (upNumber in 0..100000 && visited[upNumber] > time + 1) {
                visited[upNumber] = time + 1
                queue.addLast(upNumber to time + 1)
            }
            if (downNumber in 0..100000 && visited[downNumber] > time + 1) {
                visited[downNumber] = time + 1
                queue.addLast(downNumber to time + 1)
            }
        }
        return answer
    }
}

fun main() {
    `숨바꼭질 3`().solve()
}